package edu.utah.hci.auto;

public class GNomExSample {
	
	//fields
	private String requestId;
	private String sampleId;
	private String species;
	private String requestCreationDate;
	
	public GNomExSample(String[] results) {
		requestId = results[0];
		sampleId = results[1];
		species = results[2];
		requestCreationDate = results[3];
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(requestId); sb.append("\t");
		sb.append(sampleId); sb.append("\t");
		sb.append(species); sb.append("\t");
		sb.append(requestCreationDate);
		return sb.toString();
	}

	public String getRequestId() {
		return requestId;
	}

	public String getSampleId() {
		return sampleId;
	}

	public String getSpecies() {
		return species;
	}

	public String getRequestCreationDate() {
		return requestCreationDate;
	}

}
