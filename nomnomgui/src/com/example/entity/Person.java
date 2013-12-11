package com.example.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {
	private String gender;
	private int foodIntake;

	// images
	private int imageID;
	private int evaluationImageID;

	public Person(String gender, int imageID, int evaluationImageID) {
		this.gender = gender;
		this.imageID = imageID;
		this.evaluationImageID = evaluationImageID;
	}
	
	public Person(){
		
	}

	public Person(Parcel in) {
		this();
		readFromParcel(in);
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel out, int arg1) {
		// TODO Auto-generated method stub
		out.writeString(gender);
		out.writeInt(foodIntake);
		out.writeInt(imageID);
		out.writeInt(evaluationImageID);
	}

	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
		public Person createFromParcel(Parcel in) {
			return new Person(in);
		}

		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

	private void readFromParcel(Parcel in) {
		gender = in.readString();
		foodIntake = in.readInt();
		imageID = in.readInt();
		evaluationImageID = in.readInt();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getImageID() {
		return imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public int getEvaluationImageID() {
		return evaluationImageID;
	}

	public void setEvaluationImageID(int evaluationImageID) {
		this.evaluationImageID = evaluationImageID;
	}

	
}
