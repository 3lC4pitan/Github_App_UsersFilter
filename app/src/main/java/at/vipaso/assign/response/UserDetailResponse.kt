package at.vipaso.assign.response

import com.google.gson.annotations.SerializedName

data class UserDetailResponse(

    @field:SerializedName("gists_url")
    val gistsUrl: String,

    @field:SerializedName("repos_url")
    val reposUrl: String,

    @field:SerializedName("following_url")
    val followingUrl: String,

    @field:SerializedName("twitter_username")  //todo used
    val twitterUsername: Any,

    @field:SerializedName("bio")  //todo used
    val bio: Any,

    @field:SerializedName("created_at")  //todo used
    val createdAt: String,

    @field:SerializedName("login")  //todo used
    val login: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("blog")  //todo used
    val blog: String,

    @field:SerializedName("subscriptions_url")
    val subscriptionsUrl: String,

    @field:SerializedName("updated_at")  //todo used
    val updatedAt: String,

    @field:SerializedName("site_admin")
    val siteAdmin: Boolean,

    @field:SerializedName("company")  //todo used
    val company: String,

    @field:SerializedName("id")  //todo used
    val id: Int,

    @field:SerializedName("public_repos")  //todo used
    val publicRepos: Int,

    @field:SerializedName("gravatar_id")
    val gravatarId: String,

    @field:SerializedName("email")  //todo used
    val email: Any,

    @field:SerializedName("organizations_url")
    val organizationsUrl: String,

    @field:SerializedName("hireable")  //todo used
    val hireable: Any,

    @field:SerializedName("starred_url")
    val starredUrl: String,

    @field:SerializedName("followers_url")
    val followersUrl: String,

    @field:SerializedName("public_gists")  //todo used
    val publicGists: Int,

    @field:SerializedName("url")
    val url: String,

    @field:SerializedName("received_events_url")
    val receivedEventsUrl: String,

    @field:SerializedName("followers")  //todo used
    val followers: Int,

    @field:SerializedName("avatar_url")  //todo used
    val avatarUrl: String,

    @field:SerializedName("events_url")
    val eventsUrl: String,

    @field:SerializedName("html_url")  //todo used
    val htmlUrl: String,

    @field:SerializedName("following")  //todo used
    val following: Int,

    @field:SerializedName("name")  //todo used
    val name: String,

    @field:SerializedName("location")  //todo used
    val location: String,

    @field:SerializedName("node_id")
    val nodeId: String
)
