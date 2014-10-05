(ns clooms.lights)

(def commands-white-v2 {
  :all {:on 53 :off 57 :nightmode 185 :full 181}
  :group_1 {:on 56 :off 59 :nightmode 187 :full 184}
  :group_2 {:on 61 :off 51 :nightmode 170 :full 189}
  :group_3 {:on 55 :off 58 :nightmode 186 :full 183}
  :group_4 {:on 50 :off 54 :nightmode 182 :full 178}})

(defn int-to-byte-array-white-v2 [ints]
  (map #(byte-array [(unchecked-byte %)(byte 0)(byte 85)]) ints))

(defn create-int-white-v2
  [group command]
  (let [group_commands (group commands-white-v2)]
    (case command
      :nightmode [(first (create-int-white-v2 group :off)) (:nightmode group_commands)]
      :full [(first (create-int-white-v2 group :on)) (:full group_commands)]
      :brightness_up [(first (create-int-white-v2 group :on)) 60]
      :brightness_down [(first (create-int-white-v2 group :on)) 52]
      :warmth_up [(first (create-int-white-v2 group :on)) 62]
      :warmth_down [(first (create-int-white-v2 group :on)) 63]
      [(command group_commands)]
      )
  ))

(defn create-bytes-white-v2
  [group command]
  (int-to-byte-array-white-v2 (create-int-white-v2 group command)))
