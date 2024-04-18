package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsActivity(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsActivityKt(provider) else armsActivityJava(provider)

private fun armsActivityKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.activityPackageName.value}
import android.os.Bundle
import android.view.View

import com.jess.arms.di.component.AppComponent
import ${provider.appPackageName.value}.R
import ${provider.appPackageName.value}.base.LogisticsBaseTitleActivity
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : LogisticsBaseTitleActivity<Activity${provider.pageName.value}Binding, ${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View, View.OnClickListener  {

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

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jess.arms.di.component.AppComponent;
import ${provider.appPackageName.value}.R;
import ${provider.appPackageName.value}.base.LogisticsBaseTitleActivity;
import ${provider.appPackageName.value}.databinding.Activity${provider.pageName.value}Binding;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends LogisticsBaseTitleActivity<Activity${provider.pageName.value}Binding, ${provider.pageName.value}Presenter> implements ${provider.pageName.value}Contract.View, View.OnClickListener{
    
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
    protected Activity${provider.pageName.value}Binding initViewBinding() {
        return Activity${provider.pageName.value}Binding.inflate(getLayoutInflater(), mParentBinding.container, true);
    }
    
    @Override
    public void initTitle(@Nullable Bundle savedInstanceState) {

    } 
    
    @Override
    public void addListener() {
        mBinding.x.setOnClickListener(this);
    }
    
    @Override
    public void doNext(@Nullable Bundle savedInstanceState) {

    }
    
    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.x) {
            
        }
    }
}
    
"""