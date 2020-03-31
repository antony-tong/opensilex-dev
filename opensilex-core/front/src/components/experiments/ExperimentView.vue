<template>

  <div class="container-fluid" v-if="experiment">
                        
    <div class="page-header">
      <div class="row align-items-end">
          <div class="col-lg-8">
              <div class="page-header-title">
                  <i class="ik ik-layers bg-phis-green"></i>
                  <div class="d-inline">
                      <h5 class="mb-1">{{ $t('component.experiment.view.title') }}{{ $t('component.common.colon') }} {{ experiment.label}}</h5>
                      <span class="uri inline-action">{{ experiment.uri }} <a href="" class="inline-action-btn" title=""><i class="ik ik-copy"></i></a></span>
                  </div>
              </div>
          </div>
          <div class="col-lg-4">
              <nav class="breadcrumb-container" aria-label="breadcrumb">
                  <ol class="breadcrumb">
                      <li class="breadcrumb-item">
                        <router-link :to="{path: '/dashboard'}" title="$t('component.experiment.view.bakcToDashboard')">
                          <i class="ik ik-grid mr-1"></i>
                          {{ $t('component.menu.dashboard') }}
                        </router-link>
                      </li>
                      <li class="breadcrumb-item">
                        <router-link :to="{path: '/experiments'}" title="$t('component.experiment.view.backToList')">
                          <i class="ik ik-layers mr-1"></i>
                          {{ $t('component.menu.experiments') }}
                        </router-link>
                      </li>
                      <li class="breadcrumb-item active">{{ experiment.label }}</li>
                  </ol>
              </nav>
          </div>
      </div>
    </div>   
  
    <div class="card">
      <div class="card-header row clearfix">
          <div class="col col-sm-6">
              <div class="card-options d-inline-block">
                  <a href="experiments.html" class="btn btn-outline-primary" :title="$t('component.experiment.view.return')"><i class="ik ik-corner-up-left"></i></a>
              </div>
          </div>
          <div class="col col-sm-3"></div> 
          <div class="col col-sm-3">
              <div class="card-options text-right">
                  <a href="#" class="view-nav mr-2" :title="$t('component.experiment.view.previous')"><i class="ik ik-chevron-left"></i>{{ $t('component.common.previous') }}</a>
                  <a href="#" class="view-nav" :title="$t('component.experiment.view.next')">{{ $t('component.common.next') }}<i class="ik ik-chevron-right"></i></a>
              </div>
          </div>                                
      </div>                                                
    </div>

    <div class="row">
      <div class="col col-xl-6" style="min-width: 400px">
          <div class="card">
              <div class="card-header">
                  <h3><i class="ik ik-clipboard"></i>Description</h3>
                  <div class="card-header-right">
                      <span v-if="!experiment.isEnded" class="badge badge-pill badge-info-phis" title="L'expérimentation est toujours active"><i class="ik ik-sun mr-1"></i>En cours</span>
                      <span class="badge badge-pill badge-info" title="Certaines données de cette expérimentation sont publiques"><i class="ik ik-users mr-1"></i>Public</span>
                  </div>
              </div>
              <div class="card-body">
                  <div class="static-field">
                      <span class="static-field-key">Alias :</span>
                      <span class="static-field-line">{{ experiment.label }}</span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">URI :</span>
                      <span class="inline-action static-field-line">{{ experiment.uri }} <a href="" data-value="http://www.phenome-fppn.fr/diaphen/DIA2018-4" class="inline-action-btn" title="Copier l'URI dans le presse-papier"><i class="ik ik-copy"></i></a></span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Période :</span>
                      <span class="static-field-line">12/02/2019 - 12/05/2019 (3 mois)</span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Objectif :</span>
                      <div class="static-field-text">{{ experiment.objective }}</div>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Commentaire :</span>
                      <div class="static-field-text">{{ experiment.comment }}</div>
                  </div>
              </div>
              <div class="card-footer text-center">
                  <h6>Mots clés</h6>
                  <span class="keyword badge badge-pill badge-dark">genetic gain</span>
                  <span class="keyword badge badge-pill badge-dark">maize</span>
                  <span class="keyword badge badge-pill badge-dark">water deficit</span>
                  <span class="keyword badge badge-pill badge-dark">field</span>
                  <span class="keyword badge badge-pill badge-dark">diaphen</span>
              </div>
          </div>
          <div class="card">
              <div class="card-header row">
                  <div class="col col-sm-8">
                      <h3 class="d-inline mr-1"><i class="ik ik-target"></i>Objets Scientifiques</h3>
                      <span class="badge badge-pill badge-info">48 plantes</span>
                      <span class="badge badge-pill badge-info">125 feuilles</span>
                  </div>
                  <div class="col col-sm-4">
                      <a href="scientific-objects.html" class="btn btn-outline-primary" title="Voir les objets scientifiques de cette expérimentation"><i class="ik ik ik-eye"></i>Voir Objets Scientifiques</a>
                  </div>
              </div>                                    
          </div>
      </div>
      <div class="col col-xl-6">
          <div class="card">
              <div class="card-header">
                  <h3><i class="ik ik-box"></i>Contexte</h3>
              </div>
              <div class="card-body">
                  <div class="static-field">
                      <span class="static-field-key">Installations :</span>
                      <span class="static-field-line"><a href="" title="Consulter cette installation">Diaphen</a></span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Projets :</span>
                      <span class="static-field-line"><a href="" title="Consulter ce projet">SolAce</a>, <a href="" title="Consulter ce projet">DROP</a></span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Campagne :</span>
                      <span class="static-field-line">2018</span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Lieux :</span>
                      <span class="static-field-line">CA10A, CA10B</span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Espèces :</span>
                      <span class="static-field-line">Maïs, Blé</span>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Groupes :</span>
                      <ul class="static-field-list">
                          <li><a href="" title="Consulter ce groupe">INRA-MISTEA-GAMMA</a></li>
                          <li><a href="" title="Consulter ce groupe">INRA-LEPSE-DROPS</a></li>
                      </ul>
                  </div>
              </div>
          </div>
          <div class="card">
              <div class="card-header">
                  <h3><i class="ik ik-users"></i>Contacts</h3>
              </div>
              <div class="card-body">
                  <div class="static-field">
                      <span class="static-field-key">Scientific supervisors :</span>
                      <ul class="static-field-list">
                          <li class="inline-action"><a href="" title="Consulter ce contact">Jean Dupont<a href="" data-value="jean.dupont@inra.fr" class="inline-action-btn" title="Copier l'adresse email de ce contact"><i class="ik ik-copy"></i></a></a></li>
                          <li class="inline-action"><a href="" title="Consulter ce contact">Gilles Simon<a href="" data-value="gilles.simon@inra.fr" class="inline-action-btn" title="Copier l'adresse email de ce contact"><i class="ik ik-copy"></i></a></a></li>
                          <li class="inline-action"><a href="" title="Consulter ce contact">Jérémie Gonzalez<a href="" data-value="jeremie.gonzalez@inra.fr" class="inline-action-btn" title="Copier l'adresse email de ce contact"><i class="ik ik-copy"></i></a></a></li>
                      </ul>
                  </div>
                  <div class="static-field">
                      <span class="static-field-key">Technical supervisors :</span>
                      <ul class="static-field-list">
                          <li class="inline-action"><a href="" title="Consulter ce contact">Jérémie Gonzalez<a href="" data-value="eremie.gonzalez@inra.fr" class="inline-action-btn" title="Copier l'adresse email de ce contact"><i class="ik ik-copy"></i></a></a></li>
                      </ul>
                  </div>
              </div>
          </div>
      </div> 
    </div>
                         
  </div>

</template>

<script lang="ts">
import { Component } from "vue-property-decorator";
import Vue from "vue";
import VueRouter from "vue-router";
import HttpResponse, { OpenSilexResponse } from "../../lib/HttpResponse";
import { ExperimentsService } from "../../lib/api/experiments.service";
import { ExperimentGetDTO } from "../../lib/model/experimentGetDTO";
import VueI18n from 'vue-i18n';
import moment from "moment";
import copy from "copy-to-clipboard";


@Component
export default class ExperimentView extends Vue {
  $opensilex: any;
  $store: any;
  $router: VueRouter;

  experiment: ExperimentGetDTO = null;

  created () {
    console.log("created()");
    this.loadExperiment();
  }

  get user() {
    return this.$store.state.user;
  }

  loadExperiment() {
    console.log("loadExperiment()");

    let uri = this.$route.params.uri;
    console.log("uri = " + uri);

    let service: ExperimentsService = this.$opensilex.getService(
      "opensilex.ExperimentsService"
    );

    service.getExperiment(this.user.getAuthorizationHeader(), uri).then((http: HttpResponse<OpenSilexResponse<ExperimentGetDTO>>) => {
      console.log(http.response);
      this.experiment = http.response.result;
    }).catch(this.$opensilex.errorHandler);
  }

}

</script>

<style scoped lang="scss">

</style>
