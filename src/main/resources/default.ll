define i64 @$add$i64_i64(i64, i64) {
entry:
    %2 = add i64 %0, %1
    ret i64 %2
}

define double @$add$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fadd double %2, %1
    ret double %3
}

define double @$add$double_i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fadd double %0, %2
    ret double %3
}

define double @$add$double_double(double, double) {
entry:
    %2 = fadd double %0, %1
    ret double %2
}

define i64 @$sub$i64_i64(i64, i64) {
entry:
    %2 = sub i64 %0, %1
    ret i64 %2
}

define double @$sub$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fsub double %2, %1
    ret double %3
}

define double @$sub$double_i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fsub double %0, %2
    ret double %3
}

define double @$sub$double_double(double, double) {
entry:
    %2 = fsub double %0, %1
    ret double %2
}

define i64 @$mul$i64_i64(i64, i64) {
entry:
    %2 = mul i64 %0, %1
    ret i64 %2
}

define double @$mul$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fmul double %2, %1
    ret double %3
}

define double @$mul$double_i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fmul double %0, %2
    ret double %3
}

define double @$mul$double_double(double, double) {
entry:
    %2 = fmul double %0, %1
    ret double %2
}

define i64 @$div$i64_i64(i64, i64) {
entry:
    %2 = sdiv i64 %0, %1
    ret i64 %2
}

define double @$div$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fdiv double %2, %1
    ret double %3
}

define double @$div$double_i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fdiv double %0, %2
    ret double %3
}

define double @$div$double_double(double, double) {
entry:
    %2 = fdiv double %0, %1
    ret double %2
}

define i64 @$rem$i64_i64(i64, i64) {
entry:
    %2 = srem i64 %0, %1
    ret i64 %2
}

define double @$rem$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = frem double %2, %1
    ret double %3
}

define double @$rem$double_i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = frem double %0, %2
    ret double %3
}

define double @$rem$double_double(double, double) {
entry:
    %2 = frem double %0, %1
    ret double %2
}

define i64 @$minus$i64(i64) {
entry:
    %1 = sub i64 0, %0
    ret i64 %1
}

define double @$minus$double(double) {
entry:
    %1 = fneg double %0
    ret double %1
}

define i64 @$inc$i64(i64) {
entry:
    %1 = add i64 %0, 1
    ret i64 %1
}

define double @$inc$double(double) {
entry:
    %1 = fadd double %0, 1.0
    ret double %1
}

define i64 @$dec$i64(i64) {
entry:
    %1 = sub i64 %0, 1
    ret i64 %1
}

define double @$dec$double(double) {
entry:
    %1 = fsub double %0, 1.0
    ret double %1
}

define i64 @$pow$i64_i64(i64, i64) {
entry:
    ret i64 %0
}

define double @$pow$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    ret double %2
}

define double @$pow$double_i64(double, i64) {
entry:
    ret double %0
}

define double @$pow$double_double(double, double) {
entry:
    ret double %0
}

define i64 @$root$i64_i64(i64, i64) {
entry:
    ret i64 %0
}

define double @$root$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    ret double %2
}

define double @$root$double_i64(double, i64) {
entry:
    ret double %0
}

define double @$root$double_double(double, double) {
entry:
    ret double %0
}

define i64 @$not$i64(i64) {
entry:
    %1 = xor i64 %0, -1
    ret i64 %1
}

define i64 @$and$i64_i64(i64, i64) {
entry:
    %2 = and i64 %0, %1
    ret i64 %2
}

define i64 @$xor$i64_i64(i64, i64) {
entry:
    %2 = xor i64 %0, %1
    ret i64 %2
}

define i64 @$or$i64_i64(i64, i64) {
entry:
    %2 = or i64 %0, %1
    ret i64 %2
}

define i64 @$shl$i64_i64(i64, i64) {
entry:
    %2 = shl i64 %0, %1
    ret i64 %2
}

define i64 @$shr$i64_i64(i64, i64) {
entry:
    %2 = ashr i64 %0, %1
    ret i64 %2
}

define i64 @$eq$i64_i64(i64, i64) {
entry:
    %2 = icmp eq i64 %0, %1
    %3 = zext i1 %2 to i64
    ret i64 %3
}

define i64 @$eq$double_double(double, double) {
entry:
    %2 = fcmp oeq double %0, %1
    %3 = zext i1 %2 to i64
    ret i64 %3
}

define i64 @$ne$i64_i64(i64, i64) {
entry:
    %2 = icmp ne i64 %0, %1
    %3 = zext i1 %2 to i64
    ret i64 %3
}

define i64 @$ne$double_double(double, double) {
entry:
    %2 = fcmp one double %0, %1
    %3 = zext i1 %2 to i64
    ret i64 %3
}

define i64 @$less$i64_i64(i64, i64) {
entry:
    %2 = icmp slt i64 %0, %1
    %3 = zext i1 %2 to i64
    ret i64 %3
}

define i64 @$less$i64_double(i64, double) {
entry:
    %2 = sitofp i64 %0 to double
    %3 = fcmp olt double %2, %1
    %4 = zext i1 %3 to i64
    ret i64 %4
}

define i64 @$less$double_i64(double, i64) {
entry:
    %2 = sitofp i64 %1 to double
    %3 = fcmp olt double %0, %2
    %4 = zext i1 %3 to i64
    ret i64 %4
}

define i64 @$less$double_double(double, double) {
entry:
    %2 = fcmp olt double %0, %1
    %3 = zext i1 %2 to i64
    ret i64 %3
}

define i64 @$lnot$i64(i64) {
entry:
    %1 = icmp eq i64 %0, 0
    %2 = zext i1 %1 to i64
    ret i64 %2
}

define i64 @$land$i64_i64(i64, i64) {
entry:
    %2 = icmp ne i64 %0, 0
    %3 = icmp ne i64 %1, 0
    %4 = and i1 %2, %3
    %5 = zext i1 %4 to i64
    ret i64 %5
}

define i64 @$lor$i64_i64(i64, i64) {
entry:
    %2 = icmp ne i64 %0, 0
    %3 = icmp ne i64 %1, 0
    %4 = or i1 %2, %3
    %5 = zext i1 %4 to i64
    ret i64 %5
}

define i64 @$cond$i64_i64_i64(i64, i64, i64) {
entry:
    %3 = icmp ne i64 %0, 0
    %4 = select i1 %3, i64 %1, i64 %2
    ret i64 %4
}

define double @$cond$i64_double_double(i64, double, double) {
entry:
    %3 = icmp ne i64 %0, 0
    %4 = select i1 %3, double %1, double %2
    ret double %4
}