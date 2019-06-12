# SoundPressureLevel

Android test project to capture sound levels from Android phone microphone. Will get raw plus :
amplitudeDb = 20 * Math.log10((double)Math.abs(amplitude)), as an estimate of dB level.

As of Android 9.0, this can only work the app is in the foreground, you can no longer grab audio snippets when the screen is 
off or another app is in the foreground.
