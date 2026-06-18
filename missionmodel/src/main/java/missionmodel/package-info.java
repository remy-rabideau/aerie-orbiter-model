@MissionModel(model = Mission.class)
@WithMappers(BasicValueMappers.class)
@WithMappers(CommonValueMappers.class)
@WithConfiguration(Configuration.class)
//
// Activity Types
//
// Geometry
@WithActivityType(Apoapsis.class)
@WithActivityType(Periapsis.class)
@WithActivityType(EnterOccultation.class)
@WithActivityType(ExitOccultation.class)
@WithActivityType(SpacecraftEnterEclipse.class)
@WithActivityType(SpacecraftExitEclipse.class)
@WithActivityType(AddPeriapsis.class)
@WithActivityType(AddApoapsis.class)
@WithActivityType(AddOccultations.class)
@WithActivityType(AddSpacecraftEclipses.class)
// Power
@WithActivityType(SolarArrayDeployment.class)
// Data
@MissionModel.WithActivityType(ChangeDataGenerationRate.class)
@MissionModel.WithActivityType(DeleteData.class)
@MissionModel.WithActivityType(GenerateData.class)
@MissionModel.WithActivityType(PlaybackData.class)
@MissionModel.WithActivityType(ReprioritizeData.class)
// Downlink
@WithActivityType(Downlink.class)
// Radar
@WithActivityType(Radar_Off.class)
@WithActivityType(Radar_On.class)
@WithActivityType(ChangeRadarDataMode.class)
// Observations
@WithActivityType(ImageTarget.class)
@WithActivityType(ObserveSpectrum.class)
@WithActivityType(Slew.class)
@WithActivityType(TimeTarget.class)

// @WithMetadata(name = "unit", annotation = gov.nasa.jpl.aerie.contrib.metadata.Unit.class) // for unit support
package missionmodel;

import gov.nasa.jpl.aerie.contrib.serialization.rulesets.BasicValueMappers;
import gov.nasa.jpl.aerie.merlin.framework.annotations.MissionModel;
import gov.nasa.jpl.aerie.merlin.framework.annotations.MissionModel.WithActivityType;
import gov.nasa.jpl.aerie.merlin.framework.annotations.MissionModel.WithConfiguration;
import gov.nasa.jpl.aerie.merlin.framework.annotations.MissionModel.WithMappers;
import missionmodel.data.activities.ChangeDataGenerationRate;
import missionmodel.data.activities.DeleteData;
import missionmodel.data.activities.GenerateData;
import missionmodel.data.activities.PlaybackData;
import missionmodel.data.activities.ReprioritizeData;
import missionmodel.data.mappers.CommonValueMappers;
import missionmodel.geometry.activities.atomic.Apoapsis;
import missionmodel.geometry.activities.atomic.EnterOccultation;
import missionmodel.geometry.activities.atomic.ExitOccultation;
import missionmodel.geometry.activities.atomic.Periapsis;
import missionmodel.geometry.activities.atomic.SpacecraftEnterEclipse;
import missionmodel.geometry.activities.atomic.SpacecraftExitEclipse;
import missionmodel.geometry.activities.spawner.AddApoapsis;
import missionmodel.geometry.activities.spawner.AddOccultations;
import missionmodel.geometry.activities.spawner.AddPeriapsis;
import missionmodel.geometry.activities.spawner.AddSpacecraftEclipses;
import missionmodel.observations.activities.ImageTarget;
import missionmodel.observations.activities.ObserveSpectrum;
import missionmodel.observations.activities.Slew;
import missionmodel.observations.activities.TimeTarget;
import missionmodel.power.activities.SolarArrayDeployment;
import missionmodel.radar.ChangeRadarDataMode;
import missionmodel.radar.Radar_Off;
import missionmodel.radar.Radar_On;
import missionmodel.telecom.Downlink;
