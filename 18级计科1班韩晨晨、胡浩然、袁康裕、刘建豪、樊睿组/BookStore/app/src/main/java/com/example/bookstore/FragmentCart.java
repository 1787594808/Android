package com.example.bookstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class FragmentCart extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    List<Boolean> isSlected;
    List<Book> bookList;
    List<Gou> gouList;
    List<Integer> list;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    AdapterGou adapterGou;
    View view;
    double Money;
    Button zhifu;

    public FragmentCart() {
    }

    public static FragmentCart newInstance(String param1, String param2) {
        FragmentCart fragment = new FragmentCart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        isSlected = new LinkedList<>();
        bookList = new LinkedList<>();
        gouList = new LinkedList<>();
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.recy_gou);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        zhifu = view.findViewById(R.id.zhifu);
        Money=0;
        zhifu.setText("¥" + "0.00");

        gouList = DataSupport.select("bid")
                .where("uid = ?", String.valueOf(HttpUtil.MyUser.getUid()))
                .find(Gou.class);

        getBooks();

        zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNormalDialog();
            }
        });
        return view;
    }
    private void showNormalDialog() {
        AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(view.getContext());
        normalDialog.setTitle("是否支付");
        normalDialog.setMessage("你确定要支付吗？");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(view.getContext(),"支付成功",Toast.LENGTH_SHORT).show();
                        SCDD();
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        normalDialog.show();
    }
    private void SCDD() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                list = new LinkedList<>();
                for (int i = 0; i < bookList.size(); i++) {
                    if (isSlected.get(i) == false)
                        continue;

                    list.add(i);
                    Bill bill = new Bill();
                    bill.setBid(bookList.get(i).getBid());
                    bill.setUid(HttpUtil.MyUser.getUid());

                    DataSupport.deleteAll(Gou.class, "bid = ?", String.valueOf(bookList.get(i).getBid()));

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String nowTime = sdf.format(date);
                    bill.setTime(nowTime);

                    HttpUtil.insertBill(bill);
                    HttpUtil.addxiao(bookList.get(i).getBid());
                    HttpUtil.jianqian(HttpUtil.MyUser.getUid(),bookList.get(i).getBvalue());
                }
                updataUI1();
            }
        }).start();

    }

    private void updataUI1() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<list.size();i++)
                {
                    bookList.remove(list.get(i)-i);
                    isSlected.remove(list.get(i)-i);
                }
                list.clear();
                adapterGou.notifyDataSetChanged();
                Money = 0;
                zhifu.setText("¥" + "0.00");
            }
        });
    }

    private void getBooks() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < gouList.size(); i++) {
                        bookList.add(HttpUtil.getOnlyBook(gouList.get(i).getBid()));
                    }
                    updateUI();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void updateUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < bookList.size(); i++) {
                    isSlected.add(false);
                }
                adapterGou = new AdapterGou(bookList);
                recyclerView.setAdapter(adapterGou);
            }
        });
    }

    class AdapterGou extends RecyclerView.Adapter<AdapterGou.ViewHolder> {
        private List<Book> bookList;

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView Image;
            TextView Name;
            TextView Value;
            TextView Writer;
            ImageView Cha;
            View productView;
            RadioButton gouRaio;

            public ViewHolder(View view) {
                super(view);
                Image = view.findViewById(R.id.gou_image);
                Name = view.findViewById(R.id.gou_bookname);
                Value = view.findViewById(R.id.gou_value);
                Writer = view.findViewById(R.id.gou_writer);
                gouRaio = view.findViewById(R.id.gou_radio);
                Cha = view.findViewById(R.id.cha);
                productView = view;
            }
        }

        public AdapterGou(List<Book> books) {
            bookList = books;
        }

        @NonNull
        @Override
        public AdapterGou.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gou_item, parent, false);
            final AdapterGou.ViewHolder holder = new AdapterGou.ViewHolder(view);
            holder.productView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getAdapterPosition();
                    Intent intent = new Intent(parent.getContext(), BookDetail.class);
                    intent.putExtra("BookId", bookList.get(position).getBid());
                    parent.getContext().startActivity(intent);
                }
            });
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull AdapterGou.ViewHolder holder, int position) {
            Book book = bookList.get(position);
            holder.Name.setText(book.getBname());
            holder.Writer.setText(book.getBwriter());
            Glide.with(holder.productView).load(HttpUtil.IP+ "/static/image/" + String.valueOf(book.getBid()) + "-1.jpg").into(holder.Image);
            holder.Value.setText("¥" + String.valueOf(book.getBvalue()));
            holder.gouRaio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.gouRaio.isSelected()) {
                        holder.gouRaio.setSelected(false);
                        holder.gouRaio.setChecked(false);
                        Money -= bookList.get(position).getBvalue();
                        isSlected.set(position, false);
                        double vv = new BigDecimal(Money).setScale(2, RoundingMode.DOWN).doubleValue();
                        zhifu.setText("¥" + vv);
                    } else {
                        holder.gouRaio.setSelected(true);
                        Money += bookList.get(position).getBvalue();
                        isSlected.set(position, true);
                        double vv = new BigDecimal(Money).setScale(2, RoundingMode.DOWN).doubleValue();
                        zhifu.setText("¥" + vv);
                    }
                }
            });
            holder.Cha.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DataSupport.deleteAll(Gou.class, "bid = ?", String.valueOf(bookList.get(position).getBid()));
                    bookList.remove(position);
                    isSlected.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
        @Override
        public int getItemCount() {
            return bookList.size();
        }
    }
}