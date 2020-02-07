import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MapJNI {

	public static char[][] map;
	private String mapName;
	private int goldToWin;

	private static final int LOOK_RADIUS = 5;
	
	static {
		System.load(System.getProperty("user.dir")+"/jni/libmap.jnilib");
	}

	public native int getGoldToWin();

	protected char[][] look(int x, int y) {
		char[][] reply = new char[LOOK_RADIUS][LOOK_RADIUS];
		for (int i = 0; i < LOOK_RADIUS; i++) {
			for (int j = 0; j < LOOK_RADIUS; j++) {
				int posX = x + j - LOOK_RADIUS/2;
				int posY = y + i - LOOK_RADIUS/2;
				if (posX >= 0 && posX < getMapWidth() && 
						posY >= 0 && posY < getMapHeight()){
					reply[j][i] = map[posY][posX];
				}
				else{
					reply[j][i] = '#';
				}
			}
		}
		return reply;
	}

	public native String getMapName();

	public native int getMapWidth();

	public native int getMapHeight();
	
	public native char getTile(int x, int y);

	public native void replaceTile(int x,int y, char with);
	
	public native boolean setWin(String in);
	
	public native boolean setName(String in);
	
	public void readMap(String fileName) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("maps","example_map.txt")));
			map = loadMap(reader);
		} 
		catch (FileNotFoundException e1) {
			System.err.println("default file example_map.txt not found");
			System.exit(-1);
		} 
		catch (IOException e) {
			System.err.println("map not valid");
			System.exit(-1);
		}
	}

	/**
	 * Reads the map from file.
	 *
	 * @param : BufferedReader for the map file.
	 * @return : The map as a 2D char array 
	 */
	private char[][] loadMap(BufferedReader reader) throws IOException{

		boolean error = false;
		ArrayList<char[]> tempMap = new ArrayList<char[]>();
		int width = -1;

		String in = reader.readLine();
		if (in.startsWith("name")){
			error = setName(in);
		}

		in = reader.readLine();
		if (in.startsWith("win")){
			error = setWin(in);
		}

		in = reader.readLine();
		if (in.charAt(0) == '#' && in.length() > 1){
			width = in.trim().length();
		}

		while (in != null && !error){

			char[] row = new char[in.length()];
			if  (in.length() != width){
				error = true;
			}

			for (int i = 0; i < in.length(); i++){
				row[i] = in.charAt(i);
			}

			tempMap.add(row);

			in = reader.readLine();
		}

		if (error) {
			setName("");
			setWin("");
			return null;
		}
		char[][] map = new char[tempMap.size()][width];

		for (int i=0;i<tempMap.size();i++){
			map[i] = tempMap.get(i);
		}
		return map;
	}


}
