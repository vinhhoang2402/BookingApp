package com.example.bookingapp.entity
import com.google.gson.annotations.SerializedName


data class Hotel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("result")
    val result: String
)

data class Data(
    @SerializedName("body")
    val body: Body,
)

data class Body(
    val searchResults: SearchResults,
)

data class SearchResults(
    @SerializedName("pagination")
    val pagination: Pagination,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("totalCount")
    val totalCount: Int
)

data class Pagination(
    @SerializedName("currentPage")
    val currentPage: Int,
    @SerializedName("nextPageGroup")
    val nextPageGroup: String,
    @SerializedName("nextPageNumber")
    val nextPageNumber: Int,
    @SerializedName("nextPageStartIndex")
    val nextPageStartIndex: Int,
    @SerializedName("pageGroup")
    val pageGroup: String
)

data class Result(
    @SerializedName("address")
    val address: Address,
    @SerializedName("coordinate")
    val coordinate: Coordinate,
    @SerializedName("deals")
    val deals: Deals,
    @SerializedName("geoBullets")
    val geoBullets: List<Any>,
    @SerializedName("guestReviews")
    val guestReviews: GuestReviews,
    @SerializedName("id")
    val id: Int,
    @SerializedName("isAlternative")
    val isAlternative: Boolean,
    @SerializedName("landmarks")
    val landmarks: List<Landmark>,
    @SerializedName("name")
    val name: String,
    @SerializedName("neighbourhood")
    val neighbourhood: String,
    @SerializedName("optimizedThumbUrls")
    val optimizedThumbUrls: OptimizedThumbUrls,
    @SerializedName("pimmsAttributes")
    val pimmsAttributes: String,
    @SerializedName("providerType")
    val providerType: String,
    @SerializedName("ratePlan")
    val ratePlan: RatePlan,
    @SerializedName("starRating")
    val starRating: Double,
    @SerializedName("supplierHotelId")
    val supplierHotelId: Int,
)

data class Address(
    @SerializedName("countryCode")
    val countryCode: String,
    @SerializedName("countryName")
    val countryName: String,
    @SerializedName("extendedAddress")
    val extendedAddress: String,
    @SerializedName("locality")
    val locality: String,
    @SerializedName("obfuscate")
    val obfuscate: Boolean,
    @SerializedName("postalCode")
    val postalCode: String,
    @SerializedName("region")
    val region: String,
    @SerializedName("streetAddress")
    val streetAddress: String
)

data class Coordinate(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)

class Deals(
)

data class GuestReviews(
    @SerializedName("badge")
    val badge: String,
    @SerializedName("badgeText")
    val badgeText: String,
    @SerializedName("rating")
    val rating: String,
    @SerializedName("scale")
    val scale: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("unformattedRating")
    val unformattedRating: Double
)

data class Landmark(
    @SerializedName("distance")
    val distance: String,
    @SerializedName("label")
    val label: String
)

data class OptimizedThumbUrls(
    @SerializedName("srpDesktop")
    val srpDesktop: String
)

data class RatePlan(
    @SerializedName("features")
    val features: Features,
    @SerializedName("price")
    val price: PriceX
)

data class Features(
    @SerializedName("noCCRequired")
    val noCCRequired: Boolean,
    @SerializedName("paymentPreference")
    val paymentPreference: Boolean
)

data class PriceX(
    @SerializedName("current")
    val current: String,
    @SerializedName("exactCurrent")
    val exactCurrent: Double,
    @SerializedName("old")
    val old: String
)

data class Option(
    @SerializedName("choices")
    val choices: List<Choice>,
    @SerializedName("enhancedChoices")
    val enhancedChoices: List<EnhancedChoice>,
    @SerializedName("itemMeta")
    val itemMeta: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("selectedChoiceLabel")
    val selectedChoiceLabel: String
)

data class Choice(
    @SerializedName("label")
    val label: String,
    @SerializedName("selected")
    val selected: Boolean,
    @SerializedName("value")
    val value: String
)

data class EnhancedChoice(
    @SerializedName("choices")
    val choices: List<ChoiceX>,
    @SerializedName("itemMeta")
    val itemMeta: String,
    @SerializedName("label")
    val label: String
)

data class ChoiceX(
    @SerializedName("id")
    val id: Double,
    @SerializedName("label")
    val label: String
)
