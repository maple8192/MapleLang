define i64 @$add.i64.i64(i64, i64) {
entry:
    %2 = add i64 %0, %1
    ret i64 %2
}

define double @$add.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fadd double %2, %1
    ret double %3
}

define double @$add.double.i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fadd double %0, %2
    ret double %3
}

define double @$add.double.double(double, double) {
entry:
    %2 = fadd double %0, %1
    ret double %2
}

define i64 @$sub.i64.i64(i64, i64) {
entry:
    %2 = sub i64 %0, %1
    ret i64 %2
}

define double @$sub.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fsub double %2, %1
    ret double %3
}

define double @$sub.double.i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fsub double %0, %2
    ret double %3
}

define double @$sub.double.double(double, double) {
entry:
    %2 = fsub double %0, %1
    ret double %2
}

define i64 @$mul.i64.i64(i64, i64) {
entry:
    %2 = mul i64 %0, %1
    ret i64 %2
}

define double @$mul.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fmul double %2, %1
    ret double %3
}

define double @$mul.double.i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fmul double %0, %2
    ret double %3
}

define double @$mul.double.double(double, double) {
entry:
    %2 = fmul double %0, %1
    ret double %2
}

define i64 @$div.i64.i64(i64, i64) {
entry:
    %2 = sdiv i64 %0, %1
    ret i64 %2
}

define double @$div.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fdiv double %2, %1
    ret double %3
}

define double @$div.double.i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fdiv double %0, %2
    ret double %3
}

define double @$div.double.double(double, double) {
entry:
    %2 = fdiv double %0, %1
    ret double %2
}

define i64 @$rem.i64.i64(i64, i64) {
entry:
    %2 = srem i64 %0, %1
    ret i64 %2
}

define double @$rem.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = frem double %2, %1
    ret double %3
}

define double @$rem.double.i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = frem double %0, %2
    ret double %3
}

define double @$rem.double.double(double, double) {
entry:
    %2 = frem double %0, %1
    ret double %2
}

define i64 @$minus.i64(i64) {
entry:
    %1 = sub i64 0, %0
    ret i64 %1
}

define double @$minus.double(double) {
entry:
    %1 = fneg double %0
    ret double %1
}

define i64 @$inc.i64(i64) {
entry:
    %1 = add i64 %0, 1
    ret i64 %1
}

define double @$inc.double(double) {
entry:
    %1 = fadd double %0, 1.0
    ret double %1
}

define i64 @$dec.i64(i64) {
entry:
    %1 = sub i64 %0, 1
    ret i64 %1
}

define double @$dec.double(double) {
entry:
    %1 = fsub double %0, 1.0
    ret double %1
}

define i64 @$pow.i64.i64(i64, i64) {
entry:
    ret i64 %0
}

define double @$pow.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    ret double %2
}

define double @$pow.double.i64(double, i64) {
entry:
    ret double %0
}

define double @$pow.double.double(double, double) {
entry:
    ret double %0
}

define i64 @$root.i64.i64(i64, i64) {
entry:
    ret i64 %0
}

define double @$root.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    ret double %2
}

define double @$root.double.i64(double, i64) {
entry:
    ret double %0
}

define double @$root.double.double(double, double) {
entry:
    ret double %0
}

define i64 @$not.i64(i64) {
entry:
    %1 = xor i64 %0, -1
    ret i64 %1
}

define i64 @$and.i64.i64(i64, i64) {
entry:
    %2 = and i64 %0, %1
    ret i64 %2
}

define i64 @$xor.i64.i64(i64, i64) {
entry:
    %2 = xor i64 %0, %1
    ret i64 %2
}

define i64 @$or.i64.i64(i64, i64) {
entry:
    %2 = or i64 %0, %1
    ret i64 %2
}

define i64 @$shl.i64.i64(i64, i64) {
entry:
    %2 = shl i64 %0, %1
    ret i64 %2
}

define i64 @$shr.i64.i64(i64, i64) {
entry:
    %2 = ashr i64 %0, %1
    ret i64 %2
}

define i1 @$eq.i64.i64(i64, i64) {
entry:
    %2 = icmp eq i64 %0, %1
    ret i1 %2
}

define i1 @$eq.double.double(double, double) {
entry:
    %2 = fcmp oeq double %0, %1
    ret i1 %2
}

define i1 @$eq.i1.i1(i1, i1) {
entry:
    %2 = icmp eq i1 %0, %1
    ret i1 %2
}

define i1 @$ne.i64.i64(i64, i64) {
entry:
    %2 = icmp ne i64 %0, %1
    ret i1 %2
}

define i1 @$ne.double.double(double, double) {
entry:
    %2 = fcmp one double %0, %1
    ret i1 %2
}

define i1 @$ne.i1.i1(i1, i1) {
entry:
    %2 = icmp ne i64 %0, %1
    ret i1 %2
}

define i1 @$less.i64.i64(i64, i64) {
entry:
    %2 = icmp slt i64 %0, %1
    ret i1 %2
}

define i1 @$less.i64.double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fcmp olt double %2, %1
    ret i1 %3
}

define i1 @$less.double.i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fcmp olt double %0, %2
    ret i1 %3
}

define i1 @$less.double.double(double, double) {
entry:
    %2 = fcmp olt double %0, %1
    ret i1 %2
}

define i1 @$lnot.i1(i1) {
entry:
    %1 = xor i1 %0, 1
    ret i1 %1
}

define i1 @$land.i1.i1(i1, i1) {
entry:
    %2 = and i1 %0, %1
    ret i64 %2
}

define i1 @$lor.i1.i1(i1, i1) {
entry:
    %2 = or i1 %0, %1
    ret i1 %2
}

define i64 @$cond.i1.i64.i64(i1, i64, i64) {
entry:
    %3 = select i1 %0, i64 %1, i64 %2
    ret i64 %3
}

define double @$cond.i1.double.double(i1, double, double) {
entry:
    %3 = select i1 %0, double %1, double %2
    ret double %3
}

define i1 @$cond.i1.i1.i1(i1, i1, i1) {
entry:
    %3 = select i1 %0, i1 %1, i1 %2
    ret i1 %3
}

define i64 @$to_i64.double(double) {
entry:
    %1 = fptosi double %0 to i64
    ret i64 %1
}

define i64 @$to_i64.i1(i1) {
entry:
    %1 = zext i1 %0 to i64
    ret i64 %1
}

define double @$to_double.i64(i64) {
entry:
    %1 = sitofp i64 %0 to double
    ret double %1
}

define double @$to_double.i1(i1) {
entry:
    %1 = sitofp i1 %0 to double
    ret double %1
}

define i1 @$to_i1.i64(i64) {
entry:
    %1 = icmp ne i64 %0, 0
    ret i1 %1
}