package com.stripe.android.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;
import androidx.core.os.ConfigurationCompat;
import com.stripe.android.R;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

class CountryAdapter extends ArrayAdapter {
    private Context mContext;
    List<String> mCountries;
    private Filter mFilter = new Filter() {
        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            List arrayList = new ArrayList();
            if (charSequence == null) {
                filterResults.values = CountryAdapter.this.mCountries;
                return filterResults;
            }
            String lowerCase = charSequence.toString().toLowerCase(Locale.ROOT);
            for (String next : CountryAdapter.this.mCountries) {
                if (next.toLowerCase(Locale.ROOT).startsWith(lowerCase)) {
                    arrayList.add(next);
                }
            }
            if (arrayList.size() == 0 || (arrayList.size() == 1 && ((String) arrayList.get(0)).equals(charSequence))) {
                arrayList = CountryAdapter.this.mCountries;
            }
            filterResults.values = arrayList;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            CountryAdapter.this.mSuggestions = (List) filterResults.values;
            CountryAdapter.this.notifyDataSetChanged();
        }
    };
    List<String> mSuggestions;

    public long getItemId(int i) {
        return (long) i;
    }

    CountryAdapter(Context context, List<String> list) {
        super(context, R.layout.menu_text_view);
        this.mContext = context;
        List<String> orderedCountries = getOrderedCountries(list);
        this.mCountries = orderedCountries;
        this.mSuggestions = orderedCountries;
    }

    public int getCount() {
        return this.mSuggestions.size();
    }

    public String getItem(int i) {
        return this.mSuggestions.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view instanceof TextView) {
            ((TextView) view).setText(getItem(i));
            return view;
        }
        TextView textView = (TextView) LayoutInflater.from(this.mContext).inflate(R.layout.menu_text_view, viewGroup, false);
        textView.setText(getItem(i));
        return textView;
    }

    public Filter getFilter() {
        return this.mFilter;
    }

    /* access modifiers changed from: package-private */
    public List<String> getOrderedCountries(List<String> list) {
        Collections.sort(list, new Comparator<String>() {
            public int compare(String str, String str2) {
                return str.toLowerCase(Locale.ROOT).compareTo(str2.toLowerCase(Locale.ROOT));
            }
        });
        list.remove(getCurrentLocale().getDisplayCountry());
        list.add(0, getCurrentLocale().getDisplayCountry());
        return list;
    }

    /* access modifiers changed from: package-private */
    public Locale getCurrentLocale() {
        return ConfigurationCompat.getLocales(this.mContext.getResources().getConfiguration()).get(0);
    }
}
