/**
 * PACKAGE NAME xyz.ryochin.qittaro.fragments
 * CREATED BY kosugeryou
 * CREATED AT 2014/07/27
 */
package xyz.ryochin.qittaro.fragments;

import xyz.ryochin.qittaro.models.ArticleModel;

public interface FragmentListener {
    public void showSearchEmptyMessage(String searchWord);
    public void onItemSelected(ArticleModel model);
    public void onCompletedLoggedin(boolean result);
}