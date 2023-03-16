package io.github.maple8192.maplelang.tokenizer.token

/**
 * This class represents a symbol token type.
 */
enum class SymbolType(val symbol: String) {
    Add("+"),
    Sub("-"),
    Mul("*"),
    Div("/"),
    Rem("%"),
    Inc("++"),
    Dec("--"),
    Pow("**"),
    Root("//"),
    BNot("~"),
    BAnd("&"),
    BXor("^"),
    BOr("|"),
    LSft("<<"),
    RSft(">>"),
    Eq("=="),
    NEq("!="),
    Less("<"),
    ELess("<=="),
    Greater(">"),
    EGreater(">=="),
    LNot("!"),
    LAnd("&&"),
    LOr("||"),
    Assign("="),
    AAdd("+="),
    ASub("-="),
    AMul("*="),
    ADiv("/="),
    ARem("%="),
    APow("**="),
    ARoot("//="),
    AAnd("&="),
    AXor("^="),
    AOr("|="),
    ALSft("<<="),
    ARSft(">>="),
    ChMin("<="),
    ChMax(">="),
    Exch("<=>"),
    OBracket("("),
    CBracket(")"),
    OBrace("{"),
    CBrace("}"),
    OSquare("["),
    CSquare("]"),
    Comma(","),
    End(";");

    companion object {
        val lenOrderList: List<SymbolType> by lazy { SymbolType.values().toList().sortedByDescending { it.symbol.length } }
        val symbolChars: Set<Char> by lazy { SymbolType.values().map { it.symbol[0] }.toSet() }
    }
}