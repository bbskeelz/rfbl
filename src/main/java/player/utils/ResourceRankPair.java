package player.utils;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResourceRankPair implements Serializable{

	private String resourceUrl;
	private int rank;
	
	public ResourceRankPair(String resourceUrl, int rank){
		this.resourceUrl = resourceUrl;
		this.rank = rank;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank;
		result = prime * result + ((resourceUrl == null) ? 0 : resourceUrl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResourceRankPair other = (ResourceRankPair) obj;
		if (rank != other.rank)
			return false;
		if (resourceUrl == null) {
			if (other.resourceUrl != null)
				return false;
		} else if (!resourceUrl.equals(other.resourceUrl))
			return false;
		return true;
	}


}
