package com.example.viewpager2

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val bgColor: MutableList<Int> = mutableListOf(
        android.R.color.holo_orange_light,
        android.R.color.holo_green_light,
        android.R.color.holo_blue_bright,
        android.R.color.holo_purple
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager2.adapter = PagerAdapter(bgColor)
        viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL // ViewPager2
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Toast.makeText(baseContext, "onPageSelected", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.removeGroup(Menu.NONE)
        if (viewPager2.adapter is PagerAdapter) {
            menu?.add(Menu.NONE, MENU_ID_FRAGMENT_ADAPTER, Menu.NONE, "FragmentStateAdapter Vertical")
        } else {
            menu?.add(Menu.NONE, MENU_ID_RECYCLER_ADAPTER, Menu.NONE, "FragmentStateAdapter HORIZONTAL")
        }
        menu?.add(Menu.NONE, MENU_ID_ADD_ITEM, Menu.NONE, "Add New Item")
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            MENU_ID_FRAGMENT_ADAPTER, MENU_ID_RECYCLER_ADAPTER -> updateAdapter(item.itemId)
            MENU_ID_ADD_ITEM -> addNewItem()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateAdapter(type: Int) {
        if (type == MENU_ID_RECYCLER_ADAPTER) {
            viewPager2.adapter = PagerAdapter(bgColor)
            viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        } else {
            viewPager2.adapter = PagerFragmentStateAdapter(bgColor, supportFragmentManager)
            viewPager2.orientation = ViewPager2.ORIENTATION_VERTICAL
        }
        invalidateOptionsMenu()
    }

    private fun addNewItem() {
        val newBgColor = intArrayOf(
            android.R.color.holo_red_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_green_light,
            android.R.color.holo_blue_bright
        ).random()
        bgColor.add(newBgColor)
        viewPager2.adapter?.notifyDataSetChanged()
    }

    companion object {
        private const val MENU_ID_RECYCLER_ADAPTER = 100
        private const val MENU_ID_FRAGMENT_ADAPTER = 101
        private const val MENU_ID_ADD_ITEM = 103
    }

}
