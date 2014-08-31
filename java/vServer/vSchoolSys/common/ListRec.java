package vSchoolSys.common;

import java.io.Serializable;

//°ñµ¥¼ÇÂ¼Àà
public class ListRec implements Serializable{
	private String bookName;
	private String author;
	private int freq;
	
	public ListRec(String lBookName , String lAuthor, int lFreq){
		this.bookName = lBookName;
		this.author = lAuthor;
		this.freq = lFreq;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
	
}
