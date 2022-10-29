package com.sisop.sisop.UcuLang;

/**
 *
 */
public class UcuOver implements UcuInstruction {
    @Override
    public String toString() {
        return "UcuOver(over)";
    }

    @Override
    public void execute(UcuContext context) {
        var second = context.popValue();
        var first = context.popValue();
        context.pushValue(first);
        context.pushValue(second);
        context.pushValue(first);
        context.nextInstruction();
    }
}
