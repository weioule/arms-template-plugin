package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsActivityOld(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}
import android.os.Bundle
import android.view.View

import com.jess.arms.di.component.AppComponent
import ${provider.appPackageName.value}.R
import ${provider.appPackageName.value}.base.BaseActivity
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : BaseActivity<Activity${provider.pageName.value}Binding, ${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View, View.OnClickListener  {

    override fun setupActivityComponent(appComponent: AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1, provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    override fun initViewBinding(): Activity${provider.pageName.value}Binding {
        return Activity${provider.pageName.value}Binding.inflate(layoutInflater, mParentBinding.container, true)
    }
    
    override fun initTitle(savedInstanceState: Bundle?) {
        
    }
    
    override fun addListener() {
        mBinding.x.setOnClickListener(this)
    }
    
    override fun doNext(savedInstanceState: Bundle?) {
        
    }
    
    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.x ->{
                
            }
        }
    }
}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};

import static dagger.internal.Preconditions.checkNotNull;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import ${provider.appPackageName.value}.R;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends BaseActivity<${provider.pageName.value}Presenter> implements ${provider.pageName.value}Contract.View {
    
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.${provider.activityLayoutName.value}; 
    }
    
     @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        addListener();

    }

    public void addListener() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }
}
    
"""