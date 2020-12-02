package com.example.leesanghoon.writesongproject;

public class CommentVO {
	String id;
	String comment;
	String seq;

	public CommentVO(String id,String comment,String seq)
	{
		this.id=id;
		this.comment=comment;
		this.seq=seq;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "CommentVO [id=" + id + ", comment=" + comment + ", seq=" + seq + "]";
	}
	
}
