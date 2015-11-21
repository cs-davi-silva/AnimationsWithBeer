package br.com.davirodrigues.animationswithbeer.ui.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import br.com.davirodrigues.animationswithbeer.R;

public class InteractionView extends FrameLayout {

    View layout;

    public InteractionView(Context context) {
        this(context, null);
    }

    public InteractionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InteractionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        layout = inflate(getContext(), R.layout.interaction_layout, this);
    }



}
