package ru.alexbykov.nochat.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.alexbykov.nochat.R;


/**
 * @author Alex Bykov
 *         Date: 20.10.17.
 *         <p>
 *         You can contact me at me@alexbykov.ru
 */

public class NoChatInboxHolder extends NoChatBaseViewHolder {


    public TextView tvName;
    public ImageView civProfileImage;

    public NoChatInboxHolder(View itemView) {
        super(itemView);
        bindBubbleViews();
        tvName = bindView(R.id.tv_name);
        civProfileImage = bindView(R.id.civ_profile_image);
//        tvMessage = bindView(R.id.tv_message);
//        tvDate = bindView(R.id.tv_date);
//        ivImage = bindView(R.id.iv_image);
//        ltMessageRoot = bindView(R.id.lt_message_root);
//        ltMessage = bindView(R.id.lt_message);
//        ltFileContent = bindView(R.id.lt_file_content);
//        ivFileImage = bindView(R.id.iv_file_image);
//        tvFileName = bindView(R.id.tv_file_name);
//        tvFileSize = bindView(R.id.tv_file_size);
    }
}
