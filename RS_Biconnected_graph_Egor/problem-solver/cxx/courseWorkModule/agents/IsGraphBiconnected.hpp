#pragma once

#include <sc-memory/kpm/sc_agent.hpp>

#include "keynodes/keynodes.hpp"
#include "IsGraphBiconnected.generated.hpp"

namespace courseWorkNamespace{

class ASearchBiconnectedGraph : public ScAgent
{
  SC_CLASS(Agent, Event(Keynodes::question_is_graph_biconnected, ScEvent::Type::AddOutputEdge))
  SC_GENERATED_BODY()
};

} // namespace courseWorkNamespace
