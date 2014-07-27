/**
 * PACKAGE NAME com.ryochin.qittaro.apimanagers
 * CREATED BY kosugeryou
 * CREATED AT 2014/07/27
 */
package com.ryochin.qittaro.apimanagers;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.ryochin.qittaro.models.ArticleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchArticleAPIManager {

    private static final String TAG = SearchArticleAPIManager.class.getSimpleName();
    private final SearchArticleAPIManager self = this;
    private static final String API_URL = "https://qiita.com/api/v1/search";
    private static SearchArticleAPIManager instance;
    private int page;
    private boolean loading;
    private String searchWord;
    private List<ArticleModel> items;

    public static SearchArticleAPIManager getInstance() {
        if (instance == null) {
            instance = new SearchArticleAPIManager();
        }
        return instance;
    }

    private SearchArticleAPIManager() {
        this.page = 1;
        this.loading = false;
        this.items = new ArrayList<ArticleModel>();
    }

    public void reloadItems(final String searchWord, final APIManagerListener<ArticleModel> listener) {
        if (this.loading) {
            return;
        }

        this.page = 1;
        this.loading = true;


    }

    private StringRequest getRequest(final String searchWord, final int page, final APIManagerListener<ArticleModel> listener) {
        String url = API_URL + "?q=" + searchWord + "&page=" + String.valueOf(page);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e(TAG, "onResponse()");
                        self.loading = false;
                        List<ArticleModel> items = self.responseToItems(response);
                        if (items == null) {
                            listener.onError();
                        } else {
                            self.items.addAll(items);
                            listener.onCompleted(items);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse()");
                        self.loading = false;
                        listener.onError();
                    }
                }
        );
        return stringRequest;
    }

    private List<ArticleModel> responseToItems(String response) {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(response);
            int responseArrayCount = jsonArray.length();
            List<ArticleModel> items = new ArrayList<ArticleModel>(responseArrayCount);
            for (int i = 0; i < responseArrayCount; i ++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ArticleModel articleModel = new ArticleModel(jsonObject);
                items.add(articleModel);
            }
            return items;
        } catch (JSONException e) {
            Log.e(TAG, "JSONException ::", e);
            return null;
        }
    }

}
