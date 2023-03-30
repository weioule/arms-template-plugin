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
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R
import kotlinx.android.synthetic.main.${provider.activityLayoutName.value}.*

${commonAnnotation(provider)}
class ${provider.pageName.value}Activity : BaseTitleActivity<${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View {

    override fun setupActivityComponent(appComponent: AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1, provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    override fun getLayoutId(): Int {
       return R.layout.${provider.activityLayoutName.value}
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import com.jess.arms.di.component.AppComponent;
import ${provider.appPackageName.value}.base.BaseTitleActivity;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.OnClick;

${commonAnnotation(provider)}
public class ${provider.pageName.value}Activity extends BaseTitleActivity<${provider.pageName.value}Presenter> implements ${provider.pageName.value}Contract.View {

    @BindView(R.id.xx)
    TextView x;

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
    public int getLayoutId() {
        return R.layout.${provider.activityLayoutName.value};
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

    @OnClick({R.id.xx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xx:

                break;
        }
    }

}
    
"""