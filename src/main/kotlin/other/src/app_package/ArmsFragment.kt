package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.commonAnnotation

fun armsFragment(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsFragmentKt(provider) else armsFragmentJava(provider)

private fun armsFragmentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value}
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import ${provider.appPackageName.value}.${provider.pageName.value}Binding
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter
import ${provider.appPackageName.value}.R

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment : BaseFragment<${provider.pageName.value}Presenter>() , ${provider.pageName.value}Contract.View{

    open lateinit var mBinding: ${provider.appPackageName.value}.${provider.pageName.value}Binding  

    companion object {
    fun newInstance():${provider.pageName.value}Fragment {
        val fragment = ${provider.pageName.value}Fragment()
        return fragment
        }
    }
    override fun setupFragmentComponent(appComponent:AppComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .${provider.pageName.value[0].toLowerCase()}${provider.pageName.value.substring(1, provider.pageName.value.length)}Module(${provider.pageName.value}Module(this))
                .build()
                .inject(this)
    }
    
    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?):View{
        mBinding = ${provider.appPackageName.value}.${provider.pageName.value}Binding.inflate(inflater, container, false)
        return mBinding.getRoot()
    }
    
    override fun initData(savedInstanceState:Bundle?) {

    }
    
    override fun onClick(v: View?) {
        super.onClick(v)
        when (v?.id) {
            R.id.x ->
                
        }
    }
}
    
"""


fun armsFragmentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.fragmentPackageName.value};
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import ${provider.componentPackageName.value}.Dagger${provider.pageName.value}Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;
import ${provider.presenterPackageName.value}.${provider.pageName.value}Presenter;
import ${provider.appPackageName.value}.R;

${commonAnnotation(provider)}
class ${provider.pageName.value}Fragment extends BaseFragment<${provider.pageName.value}Presenter> implements ${provider.pageName.value}Contract.View{
    
    public ${provider.appPackageName.value}.${provider.pageName.value}Binding mBinding;
    
    public static ${provider.pageName.value}Fragment newInstance() {
        ${provider.pageName.value}Fragment fragment = new ${provider.pageName.value}Fragment();
        return fragment;
    }
    
    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        Dagger${provider.pageName.value}Component //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .view(this)
                .build()
                .inject(this);
    }
    
    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        mBinding = ${provider.appPackageName.value}.${provider.pageName.value}Binding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }
    
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
    
    }
    
    @Override
    @OnClick({R.id.xx})
    public void onViewClicked(View view) {
        super.onViewClicked(view);
        
    }
}
    
"""