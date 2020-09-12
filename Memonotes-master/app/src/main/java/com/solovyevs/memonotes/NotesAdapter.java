package com.solovyevs.memonotes;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.solovyevs.memonotes.Constants.*;

public class NotesAdapter extends BaseAdapter {

    // Хранит список всех элементов списка
    private List<Note> items;

    // LayoutInflater – класс, который из
    // layout-файла создает View-элемент.
    private LayoutInflater inflater;



    // Конструктор, в который передается контекст
    // для создания контролов из XML. И первоначальный список элементов.
    NotesAdapter(Context context, List<Note> items) {
        if (items == null) {
            this.items = new ArrayList<>();
        } else {
            this.items = items;
        }
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //сортировка

    void listRevers(List<Note> item)
    {
        Collections.sort(item);
        notifyDataSetChanged();
    }


    // Добавляет элемент в конец списка.
    // notifyDataSetChanged сообщает об обновлении данных и переотрисовывает.
    // Вы можете как угодно менять items в других местах.
    // Но не забывайте вызывать notifyDataSetChanged чтобы все обновилось.
    void addItem(Note item) {
        this.items.add(item);
        notifyDataSetChanged();
    }

    // Удаляет элемент списка.
    void removeItem(int position) {
        items.remove(position);
        notifyDataSetChanged();
    }

    // Обязательный метод абстрактного класса BaseAdapter.
    // Он возвращает колличество элементов списка.
    @Override
    public int getCount() {
        return items.size();
    }

    // Тоже обязательный метод.
    // Должен возвращать элемент списка на позиции - position
    @Override
    public Object getItem(int position) {
        if (position < items.size()) {
            return items.get(position);
        } else {
            return null;
        }
    }
    // И это тоже обязательный метод.
    // Возвращает идентификатор. Обычно это position.
    @Override
    public long getItemId(int position) {
        return position;
    }
    // Самый интересный обязательный метод.
    // Создает или возвращает переиспользуемый View, с новыми данными
    // для конкретной позиции. BaseAdapter – хитрый класс,
    // он не держит в памяти все View - это дорого и будет тормозить.
    // Поэтому он рисует только то что видно. Для этого есть convertView.
    // Если нет чего переиспользовать, то создается новый View.
    // А потом напоняет старую или новую View нужными данными.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_list_view, parent, false);
        }

        Note itemData = items.get(position);

        TextView title = view.findViewById(R.id.titletext);
        TextView bodytext = view.findViewById(R.id.body_note);
        TextView deadline = view.findViewById(R.id.deadline);

        GregorianCalendar now = (GregorianCalendar) Calendar.getInstance();

        if(itemData.getDeadline() < now.getTimeInMillis())
        {
            int overdueColor = view.getResources().getColor(R.color.TextRed);
            title.setTextColor(overdueColor);
            bodytext.setTextColor(overdueColor);
            deadline.setTextColor(overdueColor);

        }
        else{
            int overdueColor = view.getResources().getColor(R.color.TextButton);
            title.setTextColor(overdueColor);
            bodytext.setTextColor(overdueColor);
            deadline.setTextColor(overdueColor);
        }


        if (!itemData.getTitle().equals(EMPTY)) {
            title.setText(itemData.getTitle());
            title.setVisibility(View.VISIBLE);

        }
        else {
            title.setVisibility(View.GONE);
        }

        if (!itemData.getBodyNote().equals(EMPTY)) {
            bodytext.setText(itemData.getBodyNote());
            bodytext.setVisibility(View.VISIBLE);
        }
        else {
            bodytext.setVisibility(View.GONE);
        }
        if (!itemData.getTitle().equals(EMPTY) || !itemData.getBodyNote().equals(EMPTY)) {
            try {
                if (!itemData.getDeadline().equals(NEVER_HAPPEN)) {
                    Context cnt = view.getContext();
                    deadline.setText(DateUtils.formatDateTime(cnt,
                            itemData.getDeadline(), DateUtils.FORMAT_SHOW_DATE| DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_TIME));
                    deadline.setVisibility(View.VISIBLE);
                } else {
                    deadline.setVisibility(View.GONE);
                }
            } catch (Exception exception) {
                Log.e(TAG, EXPETION, exception);
                deadline.setVisibility(View.GONE);
            }
        }
        else {
            deadline.setVisibility(View.GONE);
        }

        return view;
    }


}
