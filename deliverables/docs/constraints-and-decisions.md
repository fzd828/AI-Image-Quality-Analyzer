# Constraints And Decisions

Draft target: keep the final version within 500 Chinese characters.

## Draft

本项目采用 Android 原生 Kotlin + Jetpack Compose 实现，优先保证真机可运行、APK 可提交、图片解码和大图处理过程可解释。算法选择传统 CV 方法，而不是优先接入深度学习模型，因为比赛周期有限，传统指标更容易验证和复现，也更适合展示我对算法局限的判断。图片格式核心支持 JPEG、PNG、WebP；HEIC 作为兼容性讨论或加分项，不作为最低承诺。为避免 12MP 以上大图导致 OOM，分析前会将图片按比例降采样到固定长边，预览图和分析图分开处理。评分维度选择清晰度、曝光、对比度、色偏/噪点，先用可解释公式得到子分，再加权得到 0-100 总分。UI 不追求复杂效果，重点保证选图、预览、评分、说明链路清晰。最终交付不仅包含 App，也包含样本对比、反例分析和 AI 协作说明，用证据证明结果可信。
