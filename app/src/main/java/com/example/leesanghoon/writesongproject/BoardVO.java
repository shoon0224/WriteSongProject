package com.example.leesanghoon.writesongproject;

//VO(Value Object)
public class BoardVO {
	int seq;
	String title;
	String id;
	String content;

	public BoardVO(int seq, String title, String id, String content) {
		this.seq=seq;
		this.title=title;
		this.id=id;
		this.content=content;
	}


	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "BoardVO [seq=" + seq + ", title=" + title + ", id=" + id + ", content=" + content + "]";
	}


}