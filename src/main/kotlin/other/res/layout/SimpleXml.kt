package other.res.layout

import other.ArmsPluginTemplateProviderImpl

fun simpleLayout(provider: ArmsPluginTemplateProviderImpl) = """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent">
    
    <TextView
        android:id="@+id/x"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textColor=""
        android:textSize=""
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        tools:text="" />

</androidx.constraintlayout.widget.ConstraintLayout>
"""