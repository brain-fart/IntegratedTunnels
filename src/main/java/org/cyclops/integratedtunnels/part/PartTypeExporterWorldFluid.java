package org.cyclops.integratedtunnels.part;

import com.google.common.collect.Lists;
import org.cyclops.integrateddynamics.api.part.aspect.IAspect;
import org.cyclops.integrateddynamics.core.part.aspect.AspectRegistry;
import org.cyclops.integrateddynamics.core.part.write.PartStateWriterBase;
import org.cyclops.integrateddynamics.part.aspect.Aspects;
import org.cyclops.integratedtunnels.core.part.PartTypeTunnelAspectsWorld;
import org.cyclops.integratedtunnels.part.aspect.TunnelAspects;

/**
 * A part that can export fluids to the world.
 * @author rubensworks
 */
public class PartTypeExporterWorldFluid extends PartTypeTunnelAspectsWorld<PartTypeExporterWorldFluid, PartStateWriterBase<PartTypeExporterWorldFluid>> {
    public PartTypeExporterWorldFluid(String name) {
        super(name);
        AspectRegistry.getInstance().register(this, Lists.<IAspect>newArrayList(
                TunnelAspects.Write.World.FLUID_BOOLEAN_EXPORT,
                TunnelAspects.Write.World.FLUID_FLUIDSTACK_EXPORT,
                TunnelAspects.Write.World.FLUID_LIST_EXPORT,
                TunnelAspects.Write.World.FLUID_PREDICATE_EXPORT
        ));
    }

    @Override
    protected PartStateWriterBase<PartTypeExporterWorldFluid> constructDefaultState() {
        return new PartStateWriterBase<PartTypeExporterWorldFluid>(Aspects.REGISTRY.getWriteAspects(this).size());
    }

    @Override
    public int getConsumptionRate(PartStateWriterBase<PartTypeExporterWorldFluid> state) {
        return state.hasVariable() ? 32 : super.getConsumptionRate(state);
    }
}
