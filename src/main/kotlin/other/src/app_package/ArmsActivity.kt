package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}

import android.os.Bundle
import android.view.View
import com.jess.arms.di.component.AppComponent
import ${provider.appPackageName.value}.base.BaseTitleActivity
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : BaseTitleActivity<Activity${provider.pageName.value}Binding, ${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View, View.OnClickListener {

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
    
    override fun doNext(savedInstanceState: Bundle?) {

    }
    
    override fun onBarcode(barcode: String, isScan: Boolean) {

    }
    
    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.x ->
                
        }
    }
}
    
"""

private fun armsActivityJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value};

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import ${provider.appPackageName.value}.R;
import ${provider.appPackageName.value}.base.BaseTitleActivity;
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends BaseTitleActivity<Activity${provider.pageName.value}Binding, ${provider.pageName.value}Presenter> implements ${provider.pageName.value}Contract.View, View.OnClickListener {

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
    public Activity${provider.pageName.value}Binding initViewBinding() {
        return Activity${provider.pageName.value}Binding.inflate(getLayoutInflater(), mParentBinding.container, true);
    }

    @Override
    public void initTitle(@Nullable Bundle savedInstanceState) {

    } 
    
    @Override
    public void doNext(@Nullable Bundle savedInstanceState) {

    }
    
    @Override
    protected void onBarcode(@NonNull String barcode, boolean isScan) {

    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.x:

                break;
        }
    }

}
    
"""