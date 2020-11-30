package com.eric.wanandroid.ui

import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.widget.CompoundButton
import com.eric.wanandroid.R
import com.eric.wanandroid.base.Config
import com.eric.wanandroid.base.ui.BaseActivity
import com.eric.wanandroid.cache.LocalCache
import com.eric.wanandroid.presenter.MainPresenterImpl
import com.eric.wanandroid.utils.DialogUtils
import com.eric.wanandroid.utils.ShareUtils
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * Created by eric on 20-10-15
 */
class SettingActivity: BaseActivity(), CompoundButton.OnCheckedChangeListener,
    View.OnClickListener {

    override fun setLayout(): Int = R.layout.activity_setting

    override fun initData() {
        setSupportActionBar(tool_bar)
        cur_header_img_id.text = LocalCache.userHeadImg
    }

    override fun initListener() {
        set_night_mode.setOnCheckedChangeListener(this)
        rl_edit_head_img.setOnClickListener(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return true
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
//        if (isChecked){
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        } else {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//        }
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.rl_edit_head_img -> editID()
        }
    }

    private fun editID(){
        DialogUtils.showEditIdDialog(
            this,
            cur_header_img_id.text.toString(),
            object: DialogUtils.EditIDListener{
                override fun id(content: String) {
                    if (TextUtils.isEmpty(content)){
                        return
                    }
                    LocalCache.userHeadImg = content
                    ShareUtils.getInstance().put(Config.SP_KEY_USERHEAD_IMG_ID, content)
                    cur_header_img_id.text = LocalCache.userHeadImg
                    MainPresenterImpl.update()
                }
            }
        ).show()
    }
}