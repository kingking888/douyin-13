package com.xp.wx.spellbook.util;

import android.text.TextUtils;
import com.xp.api.XC_MethodHook;
import com.xp.api.XposedHelpers;
import com.xp.wx.Global;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/xp/wx/spellbook/util/AvoidSealUtil$avoidSealSystem$hookStack$1", "Lcom/xp/api/XC_MethodHook;", "afterHookedMethod", "", "param", "Lcom/xp/api/XC_MethodHook$MethodHookParam;", "app_release"}, k = 1, mv = {1, 1, 13})
/* compiled from: AvoidSealUtil.kt */
public final class AvoidSealUtil$avoidSealSystem$hookStack$1 extends XC_MethodHook {
    AvoidSealUtil$avoidSealSystem$hookStack$1() {
    }

    /* access modifiers changed from: protected */
    public void afterHookedMethod(XC_MethodHook.MethodHookParam methodHookParam) {
        Intrinsics.checkParameterIsNotNull(methodHookParam, "param");
        Object result = methodHookParam.getResult();
        if (result != null) {
            ArrayList arrayList = new ArrayList();
            for (StackTraceElement stackTraceElement : (StackTraceElement[]) result) {
                if (!AvoidSealUtil.INSTANCE.checkFilterName(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                }
                if (!TextUtils.isEmpty(stackTraceElement.getFileName())) {
                    String fileName = stackTraceElement.getFileName();
                    Intrinsics.checkExpressionValueIsNotNull(fileName, "element.fileName");
                    if (fileName != null) {
                        String lowerCase = fileName.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                        if (StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) Global.KW_XPOSED, false, 2, (Object) null)) {
                            XposedHelpers.setObjectField(stackTraceElement, "fileName", "SourceFile");
                            XposedHelpers.setIntField(stackTraceElement, "lineNumber", 100);
                        }
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                }
            }
            Object[] array = arrayList.toArray(new StackTraceElement[0]);
            if (array != null) {
                methodHookParam.setResult(array);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<java.lang.StackTraceElement>");
    }
}