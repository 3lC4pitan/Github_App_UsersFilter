package at.vipaso.assign.adapter

import at.vipaso.assign.response.ItemsItem
import org.junit.Assert
import org.junit.Test


class UsersAdapterTest {
    @get:Test
    val itemCount_withEmptyList_shouldReturnZero: Unit
        get() {
            val adapter = UsersAdapter(ArrayList(), { user: ItemsItem? -> }, ArrayList())
            Assert.assertEquals(0, adapter.itemCount.toLong())
        }

    @get:Test
    val itemCount_withNonEmptyList_shouldReturnCorrectSize: Unit
        get() {
            val userList = ArrayList<ItemsItem>()
            userList.add(ItemsItem())
            userList.add(ItemsItem())
            val adapter = UsersAdapter(userList, { user: ItemsItem? -> }, ArrayList())
            Assert.assertEquals(userList.size.toLong(), adapter.itemCount.toLong())
        }
}

