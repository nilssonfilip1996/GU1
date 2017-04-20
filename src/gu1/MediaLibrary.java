package gu1;

import java.util.Iterator;

/**
 * MediaLibrary is a library class that handles Media objects, mapping them with ids using a hastable.
 * 
 * @author Jesper Anderberg, Filip Nilsson, Aron Polner, Ali Hassan, Szilveszter Dezsi
 */

public class MediaLibrary {
	private HashtableCH<String, Media> mediaLib = new HashtableCH<String, Media>();
	
	/**
	 * Returns the number of Media objects in this library.
	 * @return the number of Media objects in this library
	 */
	public int size(){
		return mediaLib.size();
	}
	
	/**
	 * Associates the specified Media object with a specified id in this library.
	 * If the library previously contained a mapping for the id, the old Media object
	 * is replaced by the specified Media object.
	 * @param id String ID to be associated with Media object 
	 * @param media Media object
	 * @return the previous Media object associated with id, or
	 *         {@code null} if there was no mapping for id.
	 */
	public Media put(String id, Media media){
		return mediaLib.put(id, media);
	}
	
	/**
	 * Returns the Media object to which the specified id is mapped,
	 * or {@code null} if this library contains no mapping for the id.
	 * @param id String ID for Media object to be returned
	 * @return the Media object to which the specified id is mapped, or
	 *         {@code null} if this library contains no mapping for the id
	 */
	public Media get(String id){
		return mediaLib.get(id);
	}
	
	/**
	 * Removes the mapping for an id from this library if it is present.
	 * Returns the Media object to which this library previously associated the id, 
	 * or {@code null} if the library contained no mapping for the id.
	 *
	 * @param id String ID for Media object to be removed
	 * @return the previous Media object associated with id, or
	 *         {@code null} if there was no mapping for id.
	 */
	public Media remove(String id){
		return mediaLib.remove(id);
	}
	
	/**
	 * Returns {@code true} if library contains a mapping for the specified id. 
	 *
	 * @param id String ID for Media object whose presence in this library is to be tested
	 * @return {@code true} if this library contains a mapping for the specified id
	 */
	public boolean contains(String id){
		return mediaLib.contains(id);
	}
	
	/**
	 * Returns an Iterator over all the Media objects contained in this library.
	 * @return an Iterator over all the Media objects contained in this library
	 */
	public Iterator<Media> availableMedia(){
		return mediaLib.values();
	}
}
