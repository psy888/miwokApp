package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {
//    @NonNull
//    @Override
    /**
     * @context - в какаой активити вставлять макет
     * @array - масив объектов которые нужно отображать в списке
     */
    public WordAdapter(Activity context, ArrayList<Word> array){
        // Здесь мы инициализируем внутреннее хранилище ArrayAdapter для контекста и списка.
        // второй аргумент используется, когда ArrayAdapter заполняет один TextView.
        // Поскольку это настраиваемый адаптер для двух TextViews и ImageView, адаптер не будет
        // использовать этот второй аргумент, поэтому это может быть любое значение. Здесь мы использовали 0.
        super(context,0,array);
    }


        /**
         * Предоставляет представление для AdapterView (ListView, GridView и т. Д.).
         * @param position Позиция в списке данных, которые должны отображаться в
         * просмотр списка элементов.
         * @param convertView Переработанное представление для заполнения.
         * @param parent Родительская ViewGroup, которая используется для инфляции.
         * @return Вид для позиции в AdapterView.
         **/
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Проверяем, используется ли существующее представление повторно, иначе раздуйте представление
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        // Получить объект {@link AndroidFlavor}, расположенный в этой позиции в списке
        Word currentWord = getItem(position);

        // Найти ImageView в макете list_item.xml с идинтификатором imageRes
        ImageView imgRes = (ImageView) listItemView.findViewById(R.id.image);
        //int imgRes = R.id.image;
        // Получить id изображения из текущего объекта и
        // установить это изображение в ImageView
        if (currentWord.getImageResource() != 0){
            imgRes.setImageResource(currentWord.getImageResource());
        }else{
            imgRes.setVisibility(View.GONE);
        }

        // Найти TextView в макете list_item.xml с идентификатором miwokWord
        TextView miwokWord = (TextView) listItemView.findViewById(R.id.miwokWord);
        // Получить слово на языке Miwok из текущего объекта Word и
        // установите этот текст на имя TextView
        miwokWord.setText(currentWord.getMiwokWord());

        // Найти TextView в макете list_item.xml с идентификатором englishWord
        TextView englishWord = (TextView) listItemView.findViewById(R.id.englishWord);
        // Получить слово на языке Miwok из текущего объекта Word и
        // установите этот текст на имя TextView
        englishWord.setText(currentWord.getTranslationWord());


        // Возвращает весь макет элемента списка (содержащий 2 TextViews и ImageView)
        // чтобы он отображался в ListView
        return listItemView;


    }
}
