import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * This is the class which contains all of the test methods for the MapJNI class.
 * @author Nasar
 *
 */
public class MapTest {

	MapJNI map;
	/*
	 * The @Before method makes sure that this method runs before all the methods annotated with @Test are called.
	 * The method initializes all objects required for the tests to run properly.
	 */
	@Before
	public void initObjects() {
		map = new MapJNI();
		map.readMap("maps/example_map.txt");
	}
	// This tests for the correct name of the example_map.txt 
	@Test  
	public void getMapNameTest1(){
		String mapName = map.getMapName();
		String correctMapName = "Very Small Labyrinth of Dooom";
		assertEquals(correctMapName, mapName);
	}

	//This tests for the correct width of the example_map.txt 
	@Test
	public void getMapWidthTest(){
		int correctWidth = 19;
		int width = map.getMapWidth();
		assertEquals(correctWidth, width);

	}

	//This tests for the correct height of the example_map.txt 
	@Test
	public void getMapHeightTest(){
		int correctHeight = 9;
		int height = map.getMapHeight();
		assertEquals(correctHeight, height);
	}

	//This tests for the correct gold needed to win of the example_map.txt 
	@Test
	public void getGoldToWinTest(){
		int correctWinGold = 1;
		int goldToWin = map.getGoldToWin();
		assertEquals(correctWinGold, goldToWin);
	}

	//This tests if the gold needed to win of the example_map.txt has been set correctly 
	@Test
	public void setWinTest1(){
		map.setWin("win 1");
		assertEquals(1, map.getGoldToWin());
	}
	//This test would work with a loadMap method in MapJNI
//	@Test
//	public void setWinTest2(){
//	map.setWin("win 2");
//	assertEquals(2, map.getGoldToWin());
//	}

	// Tests if the look method returns the correct grid of characters for the example_map.txt
	@Test
	public void lookTest1(){
		char[][] correctMap = new char[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				correctMap[i][j] = '.';
			}
		}
		char[][] lookMap = map.look(14, 5);
		assertArrayEquals(correctMap, lookMap);
	}

	// Tests if the look displays where the gold is on the map.
	@Test
	public void lookTest2(){
		char[][] correctMap = {{'.', '.', 'G', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'}};

		char[][] lookMap = map.look(13, 5);
		assertArrayEquals(correctMap, lookMap);
	}

	// Tests if the look displays the exit on the map.
	@Test
	public void lookTest3(){
		char[][] correctMap = {{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', 'E', '.', '.', '.'},
				{'.', '.', '.', '.', '.'},
				{'.', '.', '.', '.', '.'}};

		char[][] lookMap = map.look(3, 5);
		assertArrayEquals(correctMap, lookMap);
	}

	// These tests for getTile check if the method returns each possible character on the example_map.txt
	@Test
	public void getTileTest1(){
		char correctTile = '#';
		char tile = map.getTile(18, 5);
		assertEquals(correctTile, tile);
	}

	@Test
	public void getTileTest2(){
		char correctTile = '.';
		char tile = map.getTile(16, 5);
		assertEquals(correctTile, tile);
	}

	@Test
	public void getTileTest3(){
		char correctTile = 'E';
		char tile = map.getTile(3, 4);
		assertEquals(correctTile, tile);
	}

	@Test
	public void getTileTest4(){
		char correctTile = 'G';
		char tile = map.getTile(7, 2);
		assertEquals(correctTile, tile);
	}

	// These tests for the replaceTile method check if the method works for the following characters.
	@Test
	public void replaceTileTest1(){
		char correctTile = 'G';
		map.replaceTile(5, 5, 'G');
		assertEquals(correctTile, map.getTile(5, 5));
	}

	@Test
	public void replaceTileTest2(){
		char correctTile = 'E';
		map.replaceTile(5, 5, 'E');
		assertEquals(correctTile, map.getTile(5, 5));
	}

	@Test
	public void replaceTileTest3(){
		char correctTile = '.';
		map.replaceTile(5, 5, '.');
		assertEquals(correctTile, map.getTile(5, 5));
	}
}
