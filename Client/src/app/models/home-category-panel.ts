 export interface HomeCategoryPanel {
  panel_id: string
  panel_name: string;
  products: CategoryPanelProducts[];
  isMiniPanel?: boolean;
}

export interface CategoryPanel {
  panelId: string;
  panelName: string;
  miniPanel: boolean;
  brandedAds?: string;
}

export interface CategoryPanelProducts {
  productId: string;
  productImg: string;
  productName: string;
  productPrice?: number;
  minOffer?: number;
  maxOffer?: number;
}

export interface PanelProductWithPanelId {
  panelId: string;
  products: CategoryPanelProducts[]
}

export interface CategoryPanelWithProduct {
  panel: CategoryPanel;
  products: CategoryPanelProducts[]
}
