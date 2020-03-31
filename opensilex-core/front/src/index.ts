/// <reference path="../../../phis-ws/phis2-ws/front/types/phis2ws.d.ts" />

import { ApiServiceBinder } from './lib'
//import FactorList from './components/factors/FactorList.vue';
import ExperimentList from './components/experiments/ExperimentList.vue';
import ExperimentView from './components/experiments/ExperimentView.vue';

export default {
    install(Vue, options) {
        ApiServiceBinder.with(Vue.$opensilex.getServiceContainer());
    },
    
    components: {
//        "opensilex-core-FactorList": FactorList,
        "opensilex-core-ExperimentList": ExperimentList,
        "opensilex-core-ExperimentView": ExperimentView
    }
};