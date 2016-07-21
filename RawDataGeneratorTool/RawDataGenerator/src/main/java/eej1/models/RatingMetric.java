package eej1.models;

import eej1.util.MovieLensConstants;

public enum RatingMetric {

	LIKE(3.0f, 5.0f), DISLIKE(0.0f, 3.0f), HIT(3.0f, 5.0f), FLOP(
			0.0f, 3.0f), LIKE_HIT(3.5f, 5.0f), DISLIKE_HIT(2.0f, 3.0f), LIKE_FLOP(
			1.0f, 3.5f), DISLIKE_FLOP(0.0f, 2.5f);

	RatingMetric(float lLimit, float uLimit) {
		this.lLimit = lLimit;
		this.uLimit = uLimit;
	}

	float lLimit;
	float uLimit;

	/**
	 * @return the lLimit
	 */
	public float getlLimit() {
		return lLimit;
	}

	/**
	 * @param lLimit
	 *            the lLimit to set
	 */
	public void setlLimit(float lLimit) {
		this.lLimit = lLimit;
	}

	/**
	 * @return the uLimit
	 */
	public float getuLimit() {
		return uLimit;
	}

	/**
	 * @param uLimit
	 *            the uLimit to set
	 */
	public void setuLimit(float uLimit) {
		this.uLimit = uLimit;
	}
	
	/**
	 * @param ratingMName
	 * @return
	 */
	public static RatingMetric getRatingMetricByrMName(String ratingMName) {
		return RatingMetric.valueOf(ratingMName);
	}

	public static RatingMetric getUserTaste(int value) {
		if (MovieLensConstants.ZERO == value) {
			return LIKE;
		}
		return DISLIKE;
	}

	public static RatingMetric getMovieFuture(int value) {
		if (MovieLensConstants.ZERO == value) {
			return FLOP;
		}
		return HIT;
	}
}