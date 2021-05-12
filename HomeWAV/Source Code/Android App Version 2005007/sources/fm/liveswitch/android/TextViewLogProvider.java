package fm.liveswitch.android;

import android.app.Activity;
import android.text.Layout;
import android.widget.TextView;
import fm.liveswitch.LogEvent;
import fm.liveswitch.LogLevel;
import fm.liveswitch.LogProvider;
import java.io.PrintWriter;
import java.io.StringWriter;

public class TextViewLogProvider extends LogProvider {
    private Activity _activity;
    /* access modifiers changed from: private */
    public TextView _textView;

    public TextViewLogProvider(Activity activity, TextView textView) {
        this(activity, textView, LogLevel.Debug);
    }

    public TextViewLogProvider(Activity activity, TextView textView, LogLevel logLevel) {
        this._activity = activity;
        this._textView = textView;
        setLevel(logLevel);
    }

    public void writeLine(final String str) {
        this._activity.runOnUiThread(new Runnable() {
            public void run() {
                TextView access$000 = TextViewLogProvider.this._textView;
                access$000.setText(TextViewLogProvider.this._textView.getText() + str + "\n");
                TextViewLogProvider.this._textView.post(new Runnable() {
                    public void run() {
                        Layout layout = TextViewLogProvider.this._textView.getLayout();
                        if (layout != null) {
                            int lineTop = layout.getLineTop(TextViewLogProvider.this._textView.getLineCount()) - TextViewLogProvider.this._textView.getHeight();
                            if (lineTop > 0) {
                                TextViewLogProvider.this._textView.scrollTo(0, lineTop);
                            } else {
                                TextViewLogProvider.this._textView.scrollTo(0, 0);
                            }
                        }
                    }
                });
            }
        });
    }

    /* access modifiers changed from: protected */
    public void doLog(LogEvent logEvent) {
        StringWriter stringWriter = new StringWriter();
        logEvent.getException().printStackTrace(new PrintWriter(stringWriter));
        writeLine(String.format("%s %s", new Object[]{getPrefix(logEvent.getLevel(), true), String.format("%s\n%s", new Object[]{logEvent.getMessage(), stringWriter.toString()})}));
    }
}
