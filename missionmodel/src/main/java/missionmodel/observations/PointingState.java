package missionmodel.observations;

/**
 * The telescope's pointing state.
 *   IDLE     - not pointed at a science target
 *   SLEWING  - moving toward a new target
 *   TRACKING - settled on a target and following it
 */
public enum PointingState {
  IDLE,
  SLEWING,
  TRACKING;
}
