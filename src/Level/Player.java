package Level;

import Enemies.Fireball;
import Engine.*;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Utils.AirGroundState;
import Utils.Direction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import Level.Tileset;
import Utils.Point;
import Utils.Stopwatch;

import javax.swing.*;


public abstract class Player extends GameObject {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected float gravity = 0;
    protected float jumpHeight = 0;
    protected float jumpDegrade = 0;
    protected float terminalVelocityY = 0;
    protected float momentumYIncrease = 0;

    // values used to handle player movement
    protected float jumpForce = 0;
    protected float momentumY = 0;
    protected float moveAmountX, moveAmountY;
    protected boolean ignoreRight = false;
    protected boolean ignoreLeft = false;
    public static boolean jumping = false;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected AirGroundState airGroundState;
    protected AirGroundState previousAirGroundState;
    protected LevelState levelState;
    protected boolean underwater;
    protected boolean milkedUp;
    protected boolean pepperedUp;
    protected boolean flashingPlayer;
    protected String previousAnimation;
    protected Stopwatch shootTimer = new Stopwatch();

    // classes that listen to player events can be added to this list
    protected ArrayList<PlayerListener> listeners = new ArrayList<>();

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key JUMP_KEY = Key.UP;
    protected Key MOVE_LEFT_KEY = Key.LEFT;
    protected Key MOVE_RIGHT_KEY = Key.RIGHT;
    protected Key CROUCH_KEY = Key.DOWN;
    protected Key SHOOT_KEY = Key.Z;
    protected Fireball fireball;

    // if true, player cannot be hurt by enemies (good for testing)
    protected boolean isInvincible = false;

    // variables for used for invincibility frames
    protected Timer timer;
    protected int iFrameTime;

    // sound handlers for jumping and dying
    protected SoundsHandler jumpSound;
    protected SoundsHandler deathSound;
    protected boolean deathPlayedOnce;

    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        super(spriteSheet, x, y, startingAnimationName, 1);
        facingDirection = Direction.RIGHT;
        airGroundState = AirGroundState.AIR;
        previousAirGroundState = airGroundState;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        levelState = LevelState.RUNNING;
        underwater = false;
        milkedUp = false;
        pepperedUp = false;
        flashingPlayer = false;
        previousAnimation = currentAnimationName;
        iFrameTime = 0;
        jumpSound = new SoundsHandler("jump");
        deathSound = new SoundsHandler("death");
        deathPlayedOnce = false;

        timer = new Timer(25000 / Config.FPS, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerFlash();
                if(iFrameTime > 12){
                    timer.stop();
                    isInvincible = false;
                }
                iFrameTime++;
            }
        });
        timer.setRepeats(true);
    }

    public void update() {
        moveAmountX = 0;
        moveAmountY = 0;

        // if player is currently playing through level (has not won or lost)
        if (levelState == LevelState.RUNNING) {
            applyGravity();

            //prevents user from walking off the edge of the map (fix for map boundaries)
            if(super.x < 0 - map.tileset.getScaledSpriteWidth()/2) {
                super.x += 0;
                if(Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
                    moveAmountX += walkSpeed;
                }
            }
            if(super.x > map.endBoundX - map.tileset.getScaledSpriteWidth()) {
                super.x -= 0;
                if(Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
                    moveAmountX -= walkSpeed;
                }
            }

            // update player's state and current actions, which includes things like determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            previousAirGroundState = airGroundState;

            // update player's animation
            super.update();

            // move player with respect to map collisions based on how much player needs to move this frame
            super.moveYHandleCollision(moveAmountY);
            super.moveXHandleCollision(moveAmountX);

            updateLockedKeys();
        }

        // if player has beaten level
        else if (levelState == LevelState.LEVEL_COMPLETED) {
            updateLevelCompleted();
        }

        // if player has lost level
        else if (levelState == LevelState.PLAYER_DEAD) {
            updatePlayerDead();
        }
    }

    // set swimming state on player for water tiles
    public void setPlayerSwimming(boolean setState){underwater = setState;}

    // add gravity to player, which is a downward force
    protected void applyGravity() {
        moveAmountY += gravity + momentumY;
    }

    // based on player's current state, call appropriate player state handling method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                if(Keyboard.isKeyDown(MOVE_LEFT_KEY) ^ Keyboard.isKeyDown(MOVE_RIGHT_KEY)){playerWalking();}
                else{playerStanding();}
                break;
            case CROUCHING:
                playerCrouching();
                break;
            case JUMPING:
                playerJumping();
                break;
            case SHOOTING:
                playerShooting(2);
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        // sets animation to a STAND animation based on which way player is facing
        if(milkedUp && pepperedUp){
            currentAnimationName = facingDirection == Direction.RIGHT ? "MEPPERED_STAND_RIGHT" : "MEPPERED_STAND_LEFT";
        }
        else if(milkedUp) {
            currentAnimationName = facingDirection == Direction.RIGHT ? "MILKED_STAND_RIGHT" : "MILKED_STAND_LEFT";
        }
        else if(pepperedUp){
            currentAnimationName = facingDirection == Direction.RIGHT ? "PEPPERED_STAND_RIGHT" : "PEPPERED_STAND_LEFT";
        }
        else{
            currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
        }

        // if walk left or walk right key is pressed, player enters WALKING state
        if ((Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY))) {
            playerState = PlayerState.WALKING;
        }

        // if jump key is pressed, player enters JUMPING state
        else if ((Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY))) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        // if crouch key is pressed, player enters CROUCHING state
        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }

        //if shoot key is pressed, player shoots a fireball
        else if (Keyboard.isKeyDown(SHOOT_KEY) && !underwater && pepperedUp) {
           //playerShooting(this.getY() + 20);
            playerState = PlayerState.SHOOTING;
        }

        if(flashingPlayer) {
            if (currentAnimationName != "INVINCIBLE") {
                previousAnimation = currentAnimationName;
                currentAnimationName = "INVINCIBLE";
            }
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        // sets animation to a WALK animation based on which way player is facing
        if(milkedUp && pepperedUp){
            currentAnimationName = facingDirection == Direction.RIGHT ? "MEPPERED_WALK_RIGHT" : "MEPPERED_WALK_LEFT";
        }
        else if(milkedUp) {
            currentAnimationName = facingDirection == Direction.RIGHT ? "MILKED_WALK_RIGHT" : "MILKED_WALK_LEFT";
        }
        else if(pepperedUp){
            currentAnimationName = facingDirection == Direction.RIGHT ? "PEPPERED_WALK_RIGHT" : "PEPPERED_WALK_LEFT";
        }
        else{
            currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
        }

        if(underwater){walkSpeed = 1.5f;}
        else{walkSpeed = 2.1f;}

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            moveAmountX -= walkSpeed;
            facingDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            moveAmountX += walkSpeed;
            facingDirection = Direction.RIGHT;
        }

        else if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

        // if crouch key is pressed, player enters CROUCHING state
        else if (Keyboard.isKeyDown(CROUCH_KEY)) {
            playerState = PlayerState.CROUCHING;
        }

        //if shoot key is pressed, player shoots a fireball
//        else if (Keyboard.isKeyDown(SHOOT_KEY) && !underwater && pepperedUp) {
//            playerShooting(this.getY() + 20);
//        }

        if(flashingPlayer) {
            if (currentAnimationName != "INVINCIBLE") {
                previousAnimation = currentAnimationName;
                currentAnimationName = "INVINCIBLE";
            }
        }
    }

    // player CROUCHING state logic
    protected void playerCrouching() {
        // sets animation to a CROUCH animation based on which way player is facing
        if(milkedUp && pepperedUp){
            currentAnimationName = facingDirection == Direction.RIGHT ? "MEPPERED_CROUCH_RIGHT" : "MEPPERED_CROUCH_LEFT";
        }
        else if(milkedUp) {
            currentAnimationName = facingDirection == Direction.RIGHT ? "MILKED_CROUCH_RIGHT" : "MILKED_CROUCH_LEFT";
        }
        else if(pepperedUp){
            currentAnimationName = facingDirection == Direction.RIGHT ? "PEPPERED_CROUCH_RIGHT" : "PEPPERED_CROUCH_LEFT";
        }
        else{
            currentAnimationName = facingDirection == Direction.RIGHT ? "CROUCH_RIGHT" : "CROUCH_LEFT";
        }

        // if crouch key is released, player enters STANDING state
        if (Keyboard.isKeyUp(CROUCH_KEY)) {
            playerState = PlayerState.STANDING;
        }

        // if jump key is pressed, player enters JUMPING state
        if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
            keyLocker.lockKey(JUMP_KEY);
            playerState = PlayerState.JUMPING;
        }

//        //if shoot key is pressed, player shoots a fireball
//        else if (Keyboard.isKeyDown(SHOOT_KEY) && !underwater && pepperedUp) {
//            playerShooting(this.getY() + 30);
//        }

        if(flashingPlayer) {
            if (currentAnimationName != "INVINCIBLE") {
                previousAnimation = currentAnimationName;
                currentAnimationName = "INVINCIBLE";
            }
        }
    }
    protected void playerShooting(float fireBallHeight) {
        if(shootTimer.isTimeUp()) {
            if(Keyboard.isKeyUp(SHOOT_KEY)){
                playerState = PlayerState.STANDING;
            }
            if (facingDirection == Direction.RIGHT) {
                // x = this.getX() + 50
                fireball = new Fireball(new Point(this.getX() + 12, this.getY() - 5), 0, 1000, map, false);
            }
            else{
                fireball = new Fireball(new Point(this.getX() + 12, this.getY() - 5), 0, 1000, map, false);
            }
            shootTimer.setWaitTime(1000);
            // add fireball enemy to the map for it to offically spawn in the level
            map.addEnemy(fireball);
        }
    }
    // player JUMPING state logic
    protected void playerJumping() {

        if(underwater){jumpHeight = 8; jumpDegrade = .3f; terminalVelocityY = 1.5f
        ;}
        else{jumpHeight = 14.5f; jumpDegrade = .5f; terminalVelocityY = 6;}
        // if last frame player was on ground and this frame player is still on ground, the jump needs to be setup
        if (previousAirGroundState == AirGroundState.GROUND && airGroundState == AirGroundState.GROUND) {

            // sets animation to a JUMP animation based on which way player is facing
            if(milkedUp && pepperedUp){
                currentAnimationName = facingDirection == Direction.RIGHT ? "MEPPERED_JUMP_RIGHT" : "MEPPERED_JUMP_LEFT";
            }
            else if(milkedUp) {
                currentAnimationName = facingDirection == Direction.RIGHT ? "MILKED_JUMP_RIGHT" : "MILKED_JUMP_LEFT";
            }
            else if(pepperedUp){
                currentAnimationName = facingDirection == Direction.RIGHT ? "PEPPERED_JUMP_RIGHT" : "PEPPERED_JUMP_LEFT";
            }
            else{
                currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
            }

            // player is set to be in air and then player is sent into the air
            airGroundState = AirGroundState.AIR;
            jumpForce = jumpHeight;
            jumpSound.startSound(0);
            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }
        }

        // if player is in air (currently in a jump) and has more jumpForce, continue sending player upwards

        else if (airGroundState == AirGroundState.AIR) {


            if (jumpForce > 0) {
                moveAmountY -= jumpForce;
                jumpForce -= jumpDegrade;
                if (jumpForce < 0) {
                    jumpForce = 0;
                }
            }


            // if player is moving upwards, set player's animation to jump. if player moving downwards, set player's animation to fall
            if (previousY > Math.round(y)) {
                if(milkedUp && pepperedUp){
                    currentAnimationName = facingDirection == Direction.RIGHT ? "MEPPERED_JUMP_RIGHT" : "MEPPERED_JUMP_LEFT";
                }
                else if(milkedUp) {
                    currentAnimationName = facingDirection == Direction.RIGHT ? "MILKED_JUMP_RIGHT" : "MILKED_JUMP_LEFT";
                }
                else if(pepperedUp){
                    currentAnimationName = facingDirection == Direction.RIGHT ? "PEPPERED_JUMP_RIGHT" : "PEPPERED_JUMP_LEFT";
                }
                else{
                    currentAnimationName = facingDirection == Direction.RIGHT ? "JUMP_RIGHT" : "JUMP_LEFT";
                }
            } else {
                if(milkedUp && pepperedUp){
                    currentAnimationName = facingDirection == Direction.RIGHT ? "MEPPERED_FALL_RIGHT" : "MEPPERED_FALL_LEFT";
                }
                else if(milkedUp) {
                    currentAnimationName = facingDirection == Direction.RIGHT ? "MILKED_FALL_RIGHT" : "MILKED_FALL_LEFT";
                }
                else if(pepperedUp){
                    currentAnimationName = facingDirection == Direction.RIGHT ? "PEPPERED_FALL_RIGHT" : "PEPPERED_FALL_LEFT";
                }
                else{
                    currentAnimationName = facingDirection == Direction.RIGHT ? "FALL_RIGHT" : "FALL_LEFT";
                }

            }

            // allows you to move left and right while in the air
            if (Keyboard.isKeyDown(MOVE_LEFT_KEY) && !ignoreLeft) {
                ignoreRight = true;
                facingDirection = Direction.LEFT;
                moveAmountX -= walkSpeed;
            }

//            //if shoot key is pressed, player shoots a fireball
//            if (Keyboard.isKeyDown(SHOOT_KEY) && !underwater && pepperedUp) {
//                playerShooting(this.getY() + 20);
//            }

            if(Keyboard.isKeyUp(MOVE_LEFT_KEY)){ignoreRight = false;}
            if(Keyboard.isKeyUp(MOVE_RIGHT_KEY)){ignoreLeft = false;}

            if (Keyboard.isKeyDown(MOVE_RIGHT_KEY) && !ignoreRight) {
                ignoreLeft = true;
                moveAmountX += walkSpeed;
                facingDirection = Direction.RIGHT;
            }

            // if player is falling, increases momentum as player falls so it falls faster over time
            if (moveAmountY > 0) {
                increaseMomentum();

            }

            if(underwater){
                // if jump key is pressed underwater, player swims up
                if (Keyboard.isKeyDown(JUMP_KEY) && !keyLocker.isKeyLocked(JUMP_KEY)) {
                    keyLocker.lockKey(JUMP_KEY);
                    airGroundState = AirGroundState.AIR;
                    jumpForce = jumpHeight;
                    if (jumpForce > 0) {
                        moveAmountY -= jumpForce;
                        jumpForce -= jumpDegrade;
                        if (jumpForce < 0) {
                            jumpForce = 0;
                        }
                    }
                }
            }

            if(flashingPlayer) {
                if (currentAnimationName != "INVINCIBLE") {
                    previousAnimation = currentAnimationName;
                    currentAnimationName = "INVINCIBLE";
                }
            }
        }

        // if player last frame was in air and this frame is now on ground, player enters STANDING state
        else if (previousAirGroundState == AirGroundState.AIR && airGroundState == AirGroundState.GROUND) {
            playerState = PlayerState.STANDING;
        }
    }

    // while player is in air, this is called, and will increase momentumY by a set amount until player reaches terminal velocity
    protected void increaseMomentum() {
        momentumY += momentumYIncrease;
        if (momentumY > terminalVelocityY) {
            momentumY = terminalVelocityY;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(JUMP_KEY)) {
            keyLocker.unlockKey(JUMP_KEY);
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction) {

    }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction) {
        // if player collides with a map tile below it, it is now on the ground
        // if player does not collide with a map tile below, it is in air
        if (direction == Direction.DOWN) {
            if (hasCollided) {
                momentumY = 0;
                airGroundState = AirGroundState.GROUND;
            } else {
                playerState = PlayerState.JUMPING;
                airGroundState = AirGroundState.AIR;
            }
        }

        // if player collides with map tile upwards, it means it was jumping and then hit into a ceiling -- immediately stop upwards jump velocity
        else if (direction == Direction.UP) {
            if (hasCollided) {
                jumpForce = 0;
            }
        }
    }

    // other entities can call this method to hurt the player
    public void hurtPlayer(MapEntity mapEntity) {
        if (!isInvincible) {
            // if map entity is an enemy, kill player on touch
            if (mapEntity instanceof Enemy) {
                if(mapEntity instanceof Fireball){
                    if(((Fireball) mapEntity).getEnemyFireball() && !milkedUp){
                        if(!deathPlayedOnce) {
                            deathSound.startSound(0);
                            deathPlayedOnce = true;
                        }
                        levelState = LevelState.PLAYER_DEAD;
                    }
                }
                else if(!shootTimer.isTimeUp()){
                    mapEntity.setY(-10000);
                    mapEntity.setIsRespawnable(false);
                }
                else if(milkedUp){
                    milkedUp = false;
                    pepperedUp = false;
                    startIFrames();
                }
                else{
                    if(!deathPlayedOnce) {
                        deathSound.startSound(0);
                        deathPlayedOnce = true;
                    }
                    levelState = LevelState.PLAYER_DEAD;
                }
            }
        }
    }

    // other entities can call this to tell the player they beat a level
    public void completeLevel() {
        levelState = LevelState.LEVEL_COMPLETED;
    }

    // if player has beaten level, this will be the update cycle
    public void updateLevelCompleted() {
        GamePanel.pauseTimer();
        // if player is not on ground, player should fall until it touches the ground
        if (airGroundState != AirGroundState.GROUND && map.getCamera().containsDraw(this)) {
            if(milkedUp && pepperedUp){
                currentAnimationName = "MEPPERED_FALL_RIGHT";
            }
            else if(milkedUp) {
                currentAnimationName = "MILKED_FALL_RIGHT";
            }
            else if(pepperedUp){
                currentAnimationName = "PEPPERED_FALL_RIGHT";
            }
            else{
                currentAnimationName = "FALL_RIGHT";
            }
            applyGravity();
            increaseMomentum();
            super.update();
            moveYHandleCollision(moveAmountY);
        }
        // move player to the right until it walks off screen
        else if (map.getCamera().containsDraw(this)) {
            if(milkedUp && pepperedUp){
                currentAnimationName = "MEPPERED_WALK_RIGHT";
            }
            else if(milkedUp) {
                currentAnimationName = "MILKED_WALK_RIGHT";
            }
            else if(pepperedUp){
                currentAnimationName = "PEPPERED_WALK_RIGHT";
            }
            else{
                currentAnimationName = "WALK_RIGHT";
            }
            super.update();
            moveXHandleCollision(walkSpeed);
        } else {
            // tell all player listeners that the player has finished the level
            for (PlayerListener listener : listeners) {
                listener.onLevelCompleted();
            }
        }
    }

    // if player has died, this will be the update cycle
    public void updatePlayerDead() {
        GamePanel.pauseTimer();
        // change player animation to DEATH
        if (!currentAnimationName.startsWith("DEATH")) {
            new SoundsHandler("dead");
            if (facingDirection == Direction.RIGHT) {
                if(milkedUp && pepperedUp){
                    currentAnimationName = "DEATH_MEPPERED_RIGHT";
                }
                else if(milkedUp) {
                    currentAnimationName = "DEATH_MILKED_RIGHT";
                }
                else if(pepperedUp){
                    currentAnimationName = "DEATH_PEPPERED_RIGHT";
                }
                else{
                    currentAnimationName = "DEATH_RIGHT";
                }
            } else {
                if(milkedUp && pepperedUp){
                    currentAnimationName = "DEATH_MEPPERED_LEFT";
                }
                else if(milkedUp) {
                    currentAnimationName = "DEATH_MILKED_LEFT";
                }
                else if(pepperedUp){
                    currentAnimationName = "DEATH_PEPPERED_LEFT";
                }
                else{
                    currentAnimationName = "DEATH_LEFT";
                }            }
            super.update();
        }
        // if death animation not on last frame yet, continue to play out death animation
        else if (currentFrameIndex != getCurrentAnimation().length - 1) {
          super.update();
        }
        // if death animation on last frame (it is set up not to loop back to start), player should continually fall until it goes off screen
        else if (currentFrameIndex == getCurrentAnimation().length - 1) {
            if (map.getCamera().containsDraw(this)) {
                moveY(3);
            } else {
                // tell all player listeners that the player has died in the level
                for (PlayerListener listener : listeners) {
                    listener.onDeath();
                }
            }
        }
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public AirGroundState getAirGroundState() {
        return airGroundState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public LevelState getLevelState() {
        return levelState;
    }

    public void addListener(PlayerListener listener) {
        listeners.add(listener);
    }

    public void setMapCameraState(boolean setState){
        map.setAdjustCamera(setState);
    }

    public void setMilkedUp(boolean setState){ milkedUp = setState;}

    public void setPepperedUp(boolean setState){ pepperedUp = setState;}

    // starts the player's invincibility frames
    public void startIFrames(){
        isInvincible = true;
        timer.start();
    }

    private void playerFlash(){
        flashingPlayer = !flashingPlayer;
    }
}
