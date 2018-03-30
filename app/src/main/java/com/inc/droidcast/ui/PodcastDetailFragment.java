package com.inc.droidcast.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.inc.droidcast.R;

import butterknife.BindView;

public class PodcastDetailFragment extends Fragment implements View.OnClickListener {
	@BindView(R.id.restaurantImageView) ImageView mImageLabel;
	@BindView(R.id.restaurantNameTextView) TextView mNameLabel;
	@BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
	@BindView(R.id.ratingTextView) TextView mRatingLabel;
	@BindView(R.id.websiteTextView) TextView mWebsiteLabel;
	@BindView(R.id.phoneTextView) TextView mPhoneLabel;
	@BindView(R.id.addressTextView) TextView mAddressLabel;
	@BindView(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

	private Restaurant mRestaurant;

	public static RestaurantDetailFragment newInstance(Restaurant restaurant) {
		RestaurantDetailFragment restaurantDetailFragment = new RestaurantDetailFragment();
		Bundle args = new Bundle();
		args.putParcelable("restaurant", Parcels.wrap(restaurant));
		restaurantDetailFragment.setArguments(args);
		return restaurantDetailFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRestaurant = Parcels.unwrap(getArguments().getParcelable("restaurant"));
	}
} // End of the 'RestaurantDetailFragment'