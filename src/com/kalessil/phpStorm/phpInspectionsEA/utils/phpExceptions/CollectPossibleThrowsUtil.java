package com.kalessil.phpStorm.phpInspectionsEA.utils.phpExceptions;

import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.jetbrains.php.lang.psi.elements.Method;
import com.jetbrains.php.lang.psi.elements.MethodReference;
import com.kalessil.phpStorm.phpInspectionsEA.utils.phpDoc.ThrowsResolveUtil;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashSet;

final public class CollectPossibleThrowsUtil {
    static public void collectAnnotatedExceptions(@NotNull final Method method, @NotNull HashSet<String> declaredExceptions) {
        // resolve inherit doc tags
        Collection<MethodReference> calls = PsiTreeUtil.findChildrenOfType(method, MethodReference.class);
        for (MethodReference call : calls) {
            PsiElement methodResolved = call.resolve();
            if (methodResolved instanceof Method) {
                ThrowsResolveUtil.resolveThrownExceptions((Method) methodResolved, declaredExceptions);
            }
        }
        calls.clear();

        // TODO: new statements -> inspect __construct
    }
}