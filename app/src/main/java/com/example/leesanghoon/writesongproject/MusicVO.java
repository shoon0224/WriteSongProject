package com.example.leesanghoon.writesongproject;

public class MusicVO {
	int seq;
	int musicId;
	float x,y;
	int exist;
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public int getMusicId() {
		return musicId;
	}
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getExist() {
		return exist;
	}
	public void setExist(int exist) {
		this.exist = exist;
	}
	@Override
	public String toString() {
		return "MusicVO [seq=" + seq + ", musicId=" + musicId + ", x=" + x + ", y=" + y + ", exist=" + exist + "]";
	}
	
}
