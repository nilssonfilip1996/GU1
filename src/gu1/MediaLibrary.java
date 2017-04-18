package gu1;

import java.util.Iterator;

public class MediaLibrary {
	private HashtableCH<String, Media> mediaLib = new HashtableCH<String, Media>();
	
	public int size(){
		return mediaLib.size();
	}
	
	public Media put(String key, Media media){
		return mediaLib.put(key, media);
	}
	
	public Media get(String key){
		return mediaLib.get(key);
	}
	
	public Media remove(String key){
		return mediaLib.remove(key);
	}
	
	public boolean contains(String key){
		return mediaLib.contains(key);
	}
	
	public Iterator<Media> availableMedia(){
		return mediaLib.values();
	}
}
