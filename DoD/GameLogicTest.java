import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

/**
 * This is the class which contains all of the test methods for the GameLogic class.
 * @author Nasar
 *
 */
public class GameLogicTest {

	private MapJNI map;
	private GameLogic g;

	/*
	 * The @Before method makes sure that this method runs before all the methods annotated with @Test are called.
	 * The method initializes all objects required for the tests to run properly.
	 */

	@Before
	public void initObjects() {
		map = new MapJNI();
		map.readMap("maps/example_map.txt");
		g = new GameLogic();
		g.addDoDPlayer(0);
	}

	// Checks that a player has been spawned on the map.
	@Test
	public void addDoDPlayerTest(){
		g.addDoDPlayer(1);
		DoDPlayer player1 = g.getFromPlayers(1);
		assertEquals(true, g.isAnotherPlayerOccupyingTile(player1.getXCoordinate(), player1.getYCoordinate()));
	}

	// Checks that the spawn location is not a wall.
	@Test
	public void getSpawnLocationTest(){
		int[] spawnLocation =  g.getSpawnLocation();
		if(map.getTile(spawnLocation[0], spawnLocation[1]) == '.'){
			assertEquals('.', map.getTile(spawnLocation[0], spawnLocation[1]));
		}
		if(map.getTile(spawnLocation[0], spawnLocation[1]) == 'G'){
			assertEquals('G', map.getTile(spawnLocation[0], spawnLocation[1]));
		}
		if(map.getTile(spawnLocation[0], spawnLocation[1]) == 'E'){
			assertEquals('E', map.getTile(spawnLocation[0], spawnLocation[1]));
		}
	}

	/*
	 *  The following tests for the processCommand method check if the player has moved in the right direction and 
	 *  also check that the player has not moved into a wall.
	 */
	
	@Test
	public void processCommandTest1(){
		DoDPlayer player = g.getFromPlayers(0);
		int playerY = player.getYCoordinate();
		String reply = g.processCommand("MOVE N", 0);
		int newPlayerY = player.getYCoordinate();
		if(reply == "SUCCESS"){
			assertEquals(newPlayerY, playerY - 1);
		}
		else{
			assertEquals(newPlayerY,playerY);
		}
	}

	@Test
	public void processCommandTest2(){
		DoDPlayer player = g.getFromPlayers(0);
		int playerY = player.getYCoordinate();
		String reply = g.processCommand("MOVE S", 0);
		int newPlayerY = player.getYCoordinate();
		if(reply == "SUCCESS"){
			assertEquals(newPlayerY, playerY + 1);
		}
		else{
			assertEquals(newPlayerY,playerY);
		}
	}

	@Test
	public void processCommandTest3(){
		DoDPlayer player = g.getFromPlayers(0);
		int playerX = player.getXCoordinate();
		String reply = g.processCommand("MOVE E", 0);
		int newPlayerX = player.getXCoordinate();
		if(reply == "SUCCESS"){
			assertEquals(newPlayerX, playerX + 1);
		}
		else{
			assertEquals(newPlayerX,playerX);
		}
	}

	@Test
	public void processCommandTest4(){
		DoDPlayer player = g.getFromPlayers(0);
		int playerX = player.getXCoordinate();
		String reply = g.processCommand("MOVE W", 0);
		int newPlayerX = player.getXCoordinate();
		if(reply == "SUCCESS"){
			assertEquals(newPlayerX, playerX - 1);
		}
		else{
			assertEquals(newPlayerX, playerX);
		}
	}

	/*
	 * This test for processCommand checks if calling "pickup" returns the correct string 
	 * depending on whether the player is on a gold coin or not.
	 */
	@Test
	public void processCommandTest5(){
		DoDPlayer player = g.getFromPlayers(0);
		String reply = g.processCommand("PICKUP", 0);
		if(map.getTile(player.getXCoordinate(), player.getYCoordinate()) == 'G'){
			String success = "SUCCESS, GOLD COINS: " + player.getCollectedGold();
			assertEquals(success, reply);
		}
		else {
			String fail = "FAIL" + "\n" + "There is nothing to pick up...";
			assertEquals(fail, reply);
		}
	}

	// This test for processCommand checks if calling "hello" returns the correct string.
	 
	@Test
	public void processCommandTest6(){
		String reply = g.processCommand("HELLO", 0);
		String gold = "GOLD: 1";
		assertEquals(gold, reply);
	}

	/*
	 * The following tests for the move method checks that the player does not move into a wall 
	 * when it moves in any direction.
	 */
	@Test
	public void moveTest1(){
		DoDPlayer player = g.getFromPlayers(0);
		char tile = map.getTile(player.getXCoordinate(), player.getYCoordinate() - 1); 
		String answer  = g.move(player, 'N');
		if(tile == '#'){
			assertEquals("FAIL", answer);
		}
		else{
			assertEquals("SUCCESS", answer);
		}

	}

	@Test
	public void moveTest2(){
		DoDPlayer player = g.getFromPlayers(0);
		char tile = map.getTile(player.getXCoordinate(), player.getYCoordinate() + 1);
		String answer  = g.move(player, 'S');
		if(tile == '#'){
			assertEquals("FAIL", answer);
		}
		else{
			assertEquals("SUCCESS", answer);
		}

	}

	@Test
	public void moveTest3(){
		DoDPlayer player = g.getFromPlayers(0);
		char tile = map.getTile(player.getXCoordinate() + 1, player.getYCoordinate());
		String answer  = g.move(player, 'E');
		if(tile == '#'){
			assertEquals("FAIL", answer);
		}
		else{
			assertEquals("SUCCESS", answer);
		}

	}

	@Test
	public void moveTest4(){
		DoDPlayer player = g.getFromPlayers(0);
		char tile = map.getTile(player.getXCoordinate() - 1, player.getYCoordinate());
		String answer  = g.move(player, 'W');
		if(tile == '#'){
			assertEquals("FAIL", answer);
		}
		else{
			assertEquals("SUCCESS", answer);
		}

	}

	/*
	 * The following tests for the isAnotherPlayerOccupyingTile method checks if the method actually tests
	 * if there is another player on the tile and returns the right value.
	 */
	@Test
	public void isAnotherPlayerOccupyingTileTest1(){

		DoDPlayer player1 = new DoDPlayer(0, 5, 5);
		g.putPlayers(0, player1);
		boolean occupying = g.isAnotherPlayerOccupyingTile(5,5);
		assertEquals(true, occupying);

	}
	
	/*
	 *  Here, the value returned by the method should be false since the player has not been placed
	 *  on the tile that is being checked by the method. 
	 */
	@Test
	public void isAnotherPlayerOccupyingTileTest2(){

		DoDPlayer player1 = new DoDPlayer(0, 5, 5);
		g.putPlayers(0, player1);
		boolean occupying = g.isAnotherPlayerOccupyingTile(4,5);
		assertEquals(false, occupying);

	}
	
	/*
	 *  The following tests for the pickup method check if the correct string is returned when 
	 *  a gold coin is picked up. 
	 */
	@Test
	public void pickupTest(){
		DoDPlayer player = g.getFromPlayers(0);
		String reply = g.pickup(player);
		if(map.getTile(player.getXCoordinate(), player.getYCoordinate()) == '.'){
			String fail = "FAIL" + "\n" + "There is nothing to pick up...";
			assertEquals(fail, reply);
		}
	}

	@Test
	public void pickupTest2(){
		DoDPlayer player = g.getFromPlayers(0);
		String reply = g.pickup(player);
		if(map.getTile(player.getXCoordinate(), player.getYCoordinate()) == 'G'){
			String success = "SUCCESS, GOLD COINS: " + player.getCollectedGold();
			assertEquals(success, reply);
		}
	}

}
