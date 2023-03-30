package com.example.korefugee.Guide

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.korefugee.R
import kotlinx.android.synthetic.main.page.view.*

class ViewPagerAdapter :PagerAdapter(){
    private var mContext: Context?=null


    fun ViewPagerAdapter(context: Context){
        mContext=context;
    }

    //position에 해당하는 페이지 생성
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        // page.xml 과 연결
        val view=LayoutInflater.from(container.context).inflate(R.layout.page,container,false)

        // position 설정
        view.start_num.text= (1+position).toString()

        when(position) {
            0 ->
            {
                view.explaination.text = "refugee recognition before entering(N.1) and refugee recognition while staying.(N.2)"
                view.picture.setImageResource(R.drawable.one)}
            1 ->
            {view.explaination.text = "(N.1) The Refugee Screening Qualification Survey. If the result is withdrawal, you will face a separate immigration examination"
                view.explaination2.text = "Under the Refugee Act, the Korean government should basically provide food, clothing, and shelter to those who apply for refugee status at airports. If a referral review decision is not made within seven days, you must allow entry."
                view.picture.setImageResource(R.drawable.two)}
            2 ->
            {view.explaination.text = "If a permission to enter the country is obtained, the same process as applying for refugee recognition while staying will be."
                view.picture.setImageResource(R.drawable.three)}
            3 ->
            {view.explaination.text = "After submitting documents, we will conduct an interview with an interpreter. At this interview, you will be issued about being certificated to stay (F-2 visa)."
                view.picture.setImageResource(R.drawable.four)}
            4 ->
            {view.explaination.text = "If you are not recognized as a refugee in the interview, you can file an objection with the Minister of Justice within 30 days."
                view.picture.setImageResource(R.drawable.five)}
            5 ->
            {view.explaination.text = "If you are recognized as a refugee in an objection to the Refugee Committee, you will be issued a certificate of refugee recognition and allowed to stay."
                view.explaination2.text =" A humanitarian stay permit requires an annual stay permit to be renewed and family members cannot be invited."
                view.picture.setImageResource(R.drawable.six)}
            6 ->
            {view.explaination.text = "If you are not recognized by the Refugee Commission's objection, you will be granted a humanitarian stay permit (G-1 visa), proceed with an administrative lawsuit, or be advised to leave the country."
                view.picture.setImageResource(R.drawable.seven)}
            7 ->
            {view.explaination.text = "After disapproval, you may file an administrative lawsuit to be recognized as a refugee. If it’s not recognized, you will be recommended to leave the country due to refugee disapproval."
                view.picture.setImageResource(R.drawable.eight)}
            8 ->
            {view.explaination.text = "You can find agencies to help you with the lawsuit in Maps, and documents that you need to submit during the course can be found in the Guide"
                view.picture.setImageResource(R.drawable.female)}
            9 ->
            {view.explaination.text = "Check out the “Refuge pNan” and “Hi Korea” for more information! "
                view.picture.setImageResource(R.drawable.female)}
        }
        container.addView(view)
        return view
    }

    //position에 위치한 페이지 제거
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

    //사용가능한 뷰 개수 리턴
    override fun getCount(): Int {
        return 10
    }

    //페이지뷰가 특정 키 객체(key object)와 연관 되는지 여부
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return (view==`object`)
    }

    override fun setPrimaryItem(container: ViewGroup, position: Int, `object`: Any) {
        super.setPrimaryItem(container, position, `object`)
    }

    override fun getItemPosition(`object`: Any): Int {
        return super.getItemPosition(`object`)
    }

}