fn operator add[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = add i64 %0, %1"
"  ret i64 %2"
}

fn operator add[int a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %0 to double"
"  %3 = fadd double %2, %1"
"  ret double %3"
}

fn operator add[flt a, int b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %1 to double"
"  %3 = fadd double %0, %2"
"  ret double %3"
}

fn operator add[flt a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = fadd double %0, %1"
"  ret double %2"
}

fn operator sub[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = sub i64 %0, %1"
"  ret i64 %2"
}

fn operator sub[int a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %0 to double"
"  %3 = fsub double %2, %1"
"  ret double %3"
}

fn operator sub[flt a, int b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %1 to double"
"  %3 = fsub double %0, %2"
"  ret double %3"
}

fn operator sub[flt a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = fsub double %0, %1"
"  ret double %2"
}

fn operator mul[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = mul i64 %0, %1"
"  ret i64 %2"
}

fn operator mul[int a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %0 to double"
"  %3 = fmul double %2, %1"
"  ret double %3"
}

fn operator mul[flt a, int b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %1 to double"
"  %3 = fmul double %0, %2"
"  ret double %3"
}

fn operator mul[flt a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = fmul double %0, %1"
"  ret double %2"
}

fn operator div[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = sdiv i64 %0, %1"
"  ret i64 %2"
}

fn operator div[int a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %0 to double"
"  %3 = fdiv double %2, %1"
"  ret double %3"
}

fn operator div[flt a, int b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %1 to double"
"  %3 = fdiv double %0, %2"
"  ret double %3"
}

fn operator div[flt a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = fdiv double %0, %1"
"  ret double %2"
}

fn operator rem[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = srem i64 %0, %1"
"  ret i64 %2"
}

fn operator rem[int a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %0 to double"
"  %3 = frem double %2, %1"
"  ret double %3"
}

fn operator rem[flt a, int b] flt __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %1 to double"
"  %3 = frem double %0, %2"
"  ret double %3"
}

fn operator rem[flt a, flt b] flt __inline_LLVM__ {
"entry:"
"  %2 = frem double %0, %1"
"  ret double %2"
}

fn operator minus[int a] int __inline_LLVM__ {
"entry:"
"  %1 = sub i64 0, %0"
"  ret i64 %1"
}

fn operator minus[flt a] flt __inline_LLVM__ {
"entry:"
"  %1 = fneg double %0"
"  ret double %1"
}

fn operator inc[int a] int __inline_LLVM__ {
"entry:"
"  %1 = add i64 %0, 1"
"  ret i64 %1"
}

fn operator inc[flt a] flt __inline_LLVM__ {
"entry:"
"  %1 = fadd double %0, 1.0"
"  ret double %1"
}

fn operator dec[int a] int __inline_LLVM__ {
"entry:"
"  %1 = sub i64 %0, 1"
"  ret i64 %1"
}

fn operator dec[flt a] flt __inline_LLVM__ {
"entry:"
"  %1 = fsub double %0, 1.0"
"  ret double %1"
}

fn operator not[int a] int __inline_LLVM__ {
"entry:"
"  %1 = xor i64 %0, -1"
"  ret i64 %1"
}

fn operator and[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = and i64 %0, %1"
"  ret i64 %2"
}

fn operator xor[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = xor i64 %0, %1"
"  ret i64 %2"
}

fn operator or[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = or i64 %0, %1"
"  ret i64 %2"
}

fn operator shl[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = shl i64 %0, %1"
"  ret i64 %2"
}

fn operator shr[int a, int b] int __inline_LLVM__ {
"entry:"
"  %2 = ashr i64 %0, %1"
"  ret i64 %2"
}

fn operator eq[int a, int b] bool __inline_LLVM__ {
"entry:"
"  %2 = icmp eq i64 %0, %1"
"  ret i1 %2"
}

fn operator eq[flt a, flt b] bool __inline_LLVM__ {
"entry:"
"  %2 = fcmp oeq double %0, %1"
"  ret i1 %2"
}

fn operator eq[bool a, bool b] bool __inline_LLVM__ {
"entry:"
"  %2 = icmp eq i1 %0, %1"
"  ret i1 %2"
}

fn operator ne[int a, int b] bool __inline_LLVM__ {
"entry:"
"  %2 = icmp ne i64 %0, %1"
"  ret i1 %2"
}

fn operator ne[flt a, flt b] bool __inline_LLVM__ {
"entry:"
"  %2 = fcmp one double %0, %1"
"  ret i1 %2"
}

fn operator ne[bool a, bool b] bool __inline_LLVM__ {
"entry:"
"  %2 = icmp ne i1 %0, %1"
"  ret i1 %2"
}

fn operator less[int a, int b] bool __inline_LLVM__ {
"entry:"
"  %2 = icmp slt i64 %0, %1"
"  ret i1 %2"
}

fn operator less[int a, flt b] bool __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %0 to double"
"  %3 = fcmp olt double %2, %1"
"  ret i1 %3"
}

fn operator less[flt a, int b] bool __inline_LLVM__ {
"entry:"
"  %2 = sitofp i64 %1 to double"
"  %3 = fcmp olt double %0, %2"
"  ret i1 %3"
}

fn operator less[flt a, flt b] bool __inline_LLVM__ {
"entry:"
"  %2 = fcmp olt double %0, %1"
"  ret i1 %2"
}

fn operator lnot[bool a] bool __inline_LLVM__ {
"entry:"
"  %1 = xor i1 %0, 1"
"  ret i1 %1"
}

fn operator land[bool a, bool b] bool __inline_LLVM__ {
"entry:"
"  %2 = and i1 %0, %1"
"  ret i1 %2"
}

fn operator lor[bool a, bool b] bool __inline_LLVM__ {
"entry:"
"  %2 = or i1 %0, %1"
"  ret i1 %2"
}

fn operator cond[bool a, int b, int c] int __inline_LLVM__ {
"entry:"
"  %3 = select i1 %0, i64 %1, i64 %2"
"  ret i64 %3"
}

fn operator cond[bool a, flt b, flt c] flt __inline_LLVM__ {
"entry:"
"  %3 = select i1 %0, double %1, double %2"
"  ret double %3"
}

fn operator cond[bool a, bool b, bool c] bool __inline_LLVM__ {
"entry:"
"  %3 = select i1 %0, i1 %1, i1 %2"
"  ret i1 %3"
}

fn operator to_i64[flt a] int __inline_LLVM__ {
"entry:"
"  %1 = fptosi double %0 to i64"
"  ret i64 %1"
}

fn operator to_i64[bool a] int __inline_LLVM__ {
"entry:"
"  %1 = zext i1 %0 to i64"
"  ret i64 %1"
}

fn operator to_double[int a] flt __inline_LLVM__ {
"entry:"
"  %1 = sitofp i64 %0 to double"
"  ret double %1"
}

fn operator to_double[bool a] flt __inline_LLVM__ {
"entry:"
"  %1 = sitofp i1 %0 to double"
"  ret double %1"
}

fn operator to_i1[int a] bool __inline_LLVM__ {
"entry:"
"  %1 = icmp ne i64 %0, 0"
"  ret i1 %1"
}

fn operator pow[int a, int b] int {
    int r = 1;
    while b > 0 {
        if b & 1 r *= a;
        a *= a;
        b >>= 1;
    }
    ret r;
}