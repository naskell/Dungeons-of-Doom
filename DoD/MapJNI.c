/*
 * MapJNI.c
 *
 *  Created on: 28 Apr 2017
 *      Author: Nasar
 */
#include <jni.h>
#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>
#include "MapJNI.h"

const char *mapName = "Very Small Labyrinth of Dooom";
char map[9][19] = {{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','G','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','E','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','G','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#'},
		{'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}};
int goldToWin = 1;
int mapWidth = 19;
int mapHeight = 9;

// Allows boolean values to be returned in methods that require a java boolean type to be returned.
typedef int bool;
enum { false, true };


JNIEXPORT jint JNICALL Java_MapJNI_getGoldToWin
(JNIEnv *env, jobject thisObj){
	return goldToWin;
}

/*
 * Class:     MapJNI
 * Method:    look
 * Signature: (II)[[C
 */
//JNIEXPORT jobjectArray JNICALL Java_MapJNI_look
//(JNIEnv *env, jobject thisObj, jint x, jint y){
//
//	char **reply;
//	int i;
//	int j;
//	for (i = 0; i < 5; i++) {
//		for (j = 0; j < 5; j++) {
//			int posX = x + j - 5/2;
//			int posY = y + i - 5/2;
//			if (posX >= 0 && posX < 19 &&
//					posY >= 0 && posY < 9){
//				reply[j][i] = map[posY][posX];
//			}
//			else{
//				reply[j][i] = '#';
//			}
//		}
//	}
//	return reply;
//}

/*
 * Class:     MapJNI
 * Method:    getMapName
 * Signature: ()Ljava/lang/String;
 */

JNIEXPORT jstring JNICALL Java_MapJNI_getMapName
(JNIEnv *env, jobject thisObj){
	// converts C string into a Java String which can be returned to the MapJNI.
	jstring jStr = (*env)->NewStringUTF(env, mapName);
	return jStr;
}

/*
 * Class:     MapJNI
 * Method:    getMapWidth
 * Signature: ()I
 */

// Simply returns the width of the map which is stored in a global variable.
JNIEXPORT jint JNICALL Java_MapJNI_getMapWidth
(JNIEnv *env, jobject thisObj){
	return mapWidth;
}

/*
 * Class:     MapJNI
 * Method:    getMapHeight
 * Signature: ()I
 */
// Simply returns the height of the map which is stored in a global variable.
JNIEXPORT jint JNICALL Java_MapJNI_getMapHeight
(JNIEnv *env, jobject thisObj){
	return mapHeight;
}

/*
 * Class:     MapJNI
 * Method:    getTile
 * Signature: ()C
 */
JNIEXPORT jchar JNICALL Java_MapJNI_getTile
(JNIEnv *env, jobject thisObj, jint x, jint y){
	if (y < 0 || x < 0 || y >= mapHeight || x >= mapWidth){
		// if outside the bounds of the map then the wall character is returned.
		return '#';
	}
	return map[y][x];
}

/*
 * Class:     MapJNI
 * Method:    replaceTile
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_MapJNI_replaceTile
(JNIEnv *env, jobject thisObj, jint x, jint y, jchar with){
	if (y < 0 || x < 0 || y >= mapHeight || x >= mapWidth){
		// this is outside of the bounds of the map.
	}
	else{
		// replaces tile at this position with the character.
		map[y][x] = with;
	}
}

/*
 * Class:     MapJNI
 * Method:    setWin
 * Signature: (Ljava/lang/String;)Z
 */

// This method gets called when the loadMap method is run in MapJNI which is when the server starts.
JNIEXPORT jboolean JNICALL Java_MapJNI_setWin
(JNIEnv *env, jobject thisObj, jstring in){
	// The following would work with a loadMap method.

	//	const char *cStr = (*env)->GetStringUTFChars(env, in, NULL);
	//
	//	const char *p = cStr + 4;
	//	goldToWin = *p -'0';
	//
	//	(*env)->ReleaseStringUTFChars(env, in, cStr);
	return false;
}

/*
 * Class:     MapJNI
 * Method:    setName
 * Signature: (Ljava/lang/String;)Z
 */

// This method gets called when the loadMap method is run in MapJNI which is when the server starts.
JNIEXPORT jboolean JNICALL Java_MapJNI_setName
(JNIEnv *env, jobject thisObj, jstring in){
	return false;
}

