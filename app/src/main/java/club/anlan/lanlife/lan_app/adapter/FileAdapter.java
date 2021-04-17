package club.anlan.lanlife.lan_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import club.anlan.lanlife.lan_app.R;
import club.anlan.lanlife.lan_app.domain.File;

/**
 * 文件列表适配器
 *
 * @author lan
 * @version 1.0
 * @date 2021/4/17 18:03
 */
public class FileAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<File> fileList;

    public FileAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_file_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        File file = fileList.get(position);
        viewHolder.type.setText(file.getType());
        viewHolder.name.setText(file.getName());
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

     static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView type;

        private TextView name;

//        private Button downloadBtn;
//
//        private Button deleteBtn;


         public ViewHolder(@NonNull View itemView) {
             super(itemView);
             type = itemView.findViewById(R.id.type);
             name = itemView.findViewById(R.id.name);
//             downloadBtn = itemView.findViewById(R.id.btn_download);
//             deleteBtn = itemView.findViewById(R.id.btn_delete);
         }
     }
}
